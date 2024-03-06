package com.warehousemanagement.wms.services;



import com.google.cloud.ReadChannel;

import com.google.cloud.storage.BlobId;
import com.google.cloud.storage.BlobInfo;
import com.google.cloud.storage.Storage;
import com.warehousemanagement.wms.dto.AdministratorDTO;
import com.warehousemanagement.wms.dto.OperatorTableDTO;
import com.warehousemanagement.wms.dto.WorkDaysDTO;
import com.warehousemanagement.wms.model.*;
import com.warehousemanagement.wms.repository.AdministratorRepository;
import com.warehousemanagement.wms.repository.OperatorRepository;
import com.warehousemanagement.wms.utils.CompareDateDMY;
import com.warehousemanagement.wms.utils.CompareFiles;
import com.warehousemanagement.wms.utils.ImageHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class AdministratorService {
    String folder="C:\\Users\\Pc\\Desktop\\js\\prj\\adminend\\admindashboard\\public\\img\\admins\\";
    File avatarImage=new File(folder+"avatar.jpg");
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private OperatorRepository operatorRepository;
    @Autowired
    private AdministratorRepository administratorRepository;
    @Autowired
    private Storage storage;
    public AdminWorkDays findDay(List<AdminWorkDays> workDays,Date date){
        for(AdminWorkDays workDay:workDays){
            if(CompareDateDMY.compareDates(workDay.getData(),date)){
                return workDay;
            }
        }
        return  null;
    }
    public void setAdministrator(List<Administrator> administratorsList) {
        administratorRepository.saveAll(administratorsList);
    }
    public List<OperatorTableDTO> getAdministrators(){
        List<Administrator> administrators=administratorRepository.findAll();
        List<OperatorTableDTO> administratorsDTOS=new ArrayList<>();
        for(Administrator admin: administrators){
            administratorsDTOS.add(new OperatorTableDTO(
                    admin.getId(),admin.getAvatar(),
                    admin.getNickname(),admin.getName(),
                    admin.getSurname(),admin.getEmail(),
                    admin.getPhone(),admin.getStatus()
            ));
        }
        return administratorsDTOS;
    }
    public ResponseEntity<?> getAdministrator(Integer id){
        Optional<Administrator> optionalAdministrator=administratorRepository.findById(id);
        if(!optionalAdministrator.isPresent()) return ResponseEntity.badRequest().body("Nu există așa adiministrator");
        Administrator administrator=optionalAdministrator.get();
        AdministratorDTO administratorDTO=new AdministratorDTO(
                administrator.getId(),
                administrator.getNickname(),
                administrator.getAvatar(),
                administrator.getPhone(),
                administrator.getEmail(),
                administrator.getAddress(),
                administrator.getName(),
                administrator.getSurname(),
                administrator.getStatus(),
                administrator.getAdminWorkDays().stream().map(day->
                        new WorkDaysDTO(day.getId(),day.getData(),day.getWorkedHours())
                ).collect(Collectors.toList())
        );

        return ResponseEntity.ok().body(administratorDTO);

    }


    public String updateAdministrator(Integer id, String nickname, String name,
                                      String surname, String email, Integer phone,
                                      String address, String password, String imgName,
                                      MultipartFile file) {
        try{
            Optional<Administrator> optionalOperator=administratorRepository.findById(id);
            if(!optionalOperator.isPresent()) return "Administratorul nu a fost gasit";
            Administrator administrator=optionalOperator.get();
            if(!passwordEncoder.matches(password,administrator.getPassword())) {
                return "Parola incorectă";};
            return updateAdmin(nickname, name, surname, email, phone, address, imgName, file, administrator);
        }catch (Exception e){
            return "An error occurred: " + e.getMessage();
        }
    }

    public String addAdministrator(String nickname, String name,
                                   String surname, String email,
                                   Integer phone, String address,
                                   String imgName, MultipartFile file) {
        try {
            ImageHandler imageHandler = new ImageHandler();
            Optional<Operator> existingOperator=operatorRepository.findByNickname(nickname);
            if(existingOperator.isPresent()) return "Operator cu așa nickname există.";
            Optional<Administrator> existingAdministrator=administratorRepository.findByNickname(nickname);
            if(existingAdministrator.isPresent()) return "Administrator cu așa nickname există.";
            byte[] bytes = file.getBytes();
            if(imgName.isEmpty()){
                imgName="avatar.jpg";
            } else{
                imgName=imageHandler.setImgName(imgName, folder);
                long now = java.time.Instant.now().getEpochSecond();
                String filePath = folder + imgName+now;
                BlobId blobId=BlobId.of("wmsstorage","img/admins/"+imgName);
                BlobInfo blobInfo=BlobInfo.newBuilder(blobId).setContentType("image/jpeg").build();
                storage.create(blobInfo,bytes);
            }


            String dbFilePath = "/img/admins/" + imgName;
            Administrator administrator = new Administrator(nickname,
                    passwordEncoder.encode("11111"),
                    dbFilePath,
                    email.isEmpty() ? "--" : email,
                    phone,
                    address.isEmpty() ? "--" : address,
                    name,surname,"activ");
            administratorRepository.save(administrator);
            return "Administratorul a fost creat cu succes.";
        }catch (Exception e){
            return "An error occurred: " + e.getMessage();
        }


    }

    public String updateAdministrator(Integer id, String nickname, String name,
                                      String surname, String email, Integer phone,
                                      String address, String imgName, MultipartFile file) {
        try{
            Optional<Administrator> optionalOperator=administratorRepository.findById(id);
            if(!optionalOperator.isPresent()) return "Administratorul nu a fost gasit";
            Administrator administrator=optionalOperator.get();

            return updateAdmin(nickname, name, surname, email, phone, address, imgName, file, administrator);
        }catch (Exception e){
            return "An error occurred: " + e.getMessage();
        }
    }

    private String updateAdmin(String nickname, String name, String surname, String email, Integer phone, String address, String imgName, MultipartFile file, Administrator administrator) throws IOException {
        String originalImgName=administrator.getAvatar().substring(administrator.getAvatar().lastIndexOf('/')+1);
        File fileImg=new File(folder+originalImgName);
        String dbFilePath="/img/admins/"+originalImgName;
        String filePath;
        ImageHandler imageHandler=new ImageHandler();
        if(file.isEmpty()){
            if(!originalImgName.equals(imgName) && !originalImgName.equals("avatar.jpg")){
                String newImgName=imageHandler.setImgName(imgName,folder);
                File newFile=new File(folder+newImgName);
                fileImg.renameTo(newFile);
                dbFilePath="/img/admins/"+newImgName;
            }
        }else{
            String newImgName=imageHandler.setImgName(imgName,folder);
            byte[] bytes=file.getBytes();
            filePath=folder+newImgName;
            Files.write(Paths.get(filePath), bytes);

            boolean areEqual= CompareFiles.compareFiles(avatarImage,fileImg);
            if(!areEqual){
                if(!fileImg.delete()) return "Probleme la stergera imaginii vechi";
            }

            dbFilePath="/img/admins/"+newImgName;
        }
        administrator.setName(name);
        administrator.setNickname(nickname);
        administrator.setSurname(surname);
        administrator.setEmail(email);
        administrator.setPhone(phone);
        administrator.setAddress(address);
        administrator.setAvatar(dbFilePath);
        administratorRepository.save(administrator);


        return "Administratorul a fost editat cu succes";
    }

    public String deleteAdministrator(Integer id) {
        try{
            Optional<Administrator> optionalAdminstrator=administratorRepository.findById(id);
            if(!optionalAdminstrator.isPresent()) return "Operatorul nu a fost gasit.";
            Administrator administrator=optionalAdminstrator.get();
            String originalImgName=administrator.getAvatar().substring(administrator.getAvatar().lastIndexOf('/')+1);
            File fileImg=new File(folder+originalImgName);
            boolean areEqual= CompareFiles.compareFiles(avatarImage,fileImg);
            if(!areEqual){
                if(!fileImg.delete()) return "Probleme la stergera imaginii vechi";
                administrator.setAvatar("/img/admins/avatar.jpg");
            }
            administrator.setStatus("inactiv");
            administratorRepository.save(administrator);
            return "Administratorul a fost șters";

        }catch (Exception e) {
            return "An error occurred: " + e.getMessage();
        }
    }

    public ResponseEntity<?> setWorkedHours(Integer id, String date, Integer workedHours) {
        try {
            Date newDate = new SimpleDateFormat("dd.MM.yyyy").parse(date);
            Optional<Administrator> optionalAdministrator=administratorRepository.findById(id);
            if(!optionalAdministrator.isPresent()) return  ResponseEntity.badRequest().body("Administratorul nu exista");
            Administrator administrator=optionalAdministrator.get();
            List<AdminWorkDays> adminWorkDays=administrator.getAdminWorkDays();
            AdminWorkDays foundDay=findDay(adminWorkDays,newDate );
            if(foundDay==null){
                adminWorkDays.add(new AdminWorkDays(newDate,workedHours));
            }
            else {
                foundDay.setWorkedHours(workedHours);
            }
            administrator.setAdminWorkDays(adminWorkDays);
            administratorRepository.save(administrator);
            return ResponseEntity.ok().body("datele au fost adaugate");
        }catch(ParseException e){
            return ResponseEntity.badRequest().body("Data nu e in format corect");
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("An error occurred: " + e.getMessage());
        }
    }

    public String changePassword(Integer id, String oldPassword, String newPassword) {
        try{
            Optional<Administrator> optionalAdministrator=administratorRepository.findById(id);
            if(!optionalAdministrator.isPresent()) return "Administratorul nu a fost gasit.";
            Administrator administrator=optionalAdministrator.get();
          if(!passwordEncoder.matches(oldPassword,administrator.getPassword())) return "Parolă incorectă";
            administrator.setPassword(passwordEncoder.encode(newPassword));
            administratorRepository.save(administrator);
            return "Parola a fost modificată";
        }  catch (Exception e){
        return ("An error occurred: " + e.getMessage());
    }
    }
}
