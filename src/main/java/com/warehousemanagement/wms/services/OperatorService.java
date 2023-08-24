package com.warehousemanagement.wms.services;

import com.warehousemanagement.wms.dto.*;
import com.warehousemanagement.wms.model.*;
import com.warehousemanagement.wms.repository.InvoiceRepository;
import com.warehousemanagement.wms.repository.OperatorRepository;
import com.warehousemanagement.wms.utils.CompareDateDMY;
import com.warehousemanagement.wms.utils.CompareFiles;
import com.warehousemanagement.wms.utils.FindSumForDate;
import com.warehousemanagement.wms.utils.ImageHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class OperatorService {
    String folder="C:\\Users\\Pc\\Desktop\\js\\prj\\adminend\\admindashboard\\public\\img\\operators\\";
    File avatarImage=new File(folder+"avatar.jpg");
    @Autowired
    private OperatorRepository operatorRepository;
    public OperatorWorkDays findDay(List<OperatorWorkDays> workDays,Date date){
        for(OperatorWorkDays workDay:workDays){
            if(CompareDateDMY.compareDates(workDay.getData(),date)){
                return workDay;
            }
        }
        return  null;
    }
    public void setOperator(List<Operator> operatorsList) {
        operatorRepository.saveAll(operatorsList);
    }
    public List<Operator> getOperators(){
        return operatorRepository.findAll();
    }
    public List<OperatorTableDTO> getOperatorTableDTOs(){
        List<Operator> operators=operatorRepository.findAll();
        List<OperatorTableDTO> operatorTableDTOs=new ArrayList<>();
        for(Operator operator: operators){
            operatorTableDTOs.add(convertToOperatorTableDTO(operator));
        }
        return operatorTableDTOs;
    }
    public SingleOperatorDTO getOperator(Integer id){
        Optional<Operator> optionalOperator = operatorRepository.findById(id);
        if(!optionalOperator.isPresent())
            throw new RuntimeException("Operator not found with id: " + id);
        Operator operator=optionalOperator.get();
        operator.getOperatorWorkDays().stream().forEach(day->
                System.out.println(day.toString()));
        SingleOperatorDTO operatorDTO=new SingleOperatorDTO(
                operator.getId(),
                operator.getNickname(),
                operator.getAvatar(),
                operator.getPhone(),
                operator.getEmail(),
                operator.getAddress(),
                operator.getName(),
                operator.getSurname(),
                operator.getStatus(),
                operator.getOperatorWorkDays().stream().map(day->
                    new WorkDaysDTO(day.getId(),day.getData(),day.getWorkedHours())
                ).collect(Collectors.toList()),
                operator.getInvoices().stream()
                          .sorted(Comparator.comparing(Invoice::getDate).reversed())
                        .map(invoice ->
                        new OperatorInvoiceDTO(invoice.getId(),invoice.getDate(),
                                invoice.getCustomer().getNickname(),invoice.getShipped(),invoice.getTotalPrice()))
                .collect(Collectors.toList())
        );
        operatorDTO.getWorkedDays().stream().forEach(day->
                System.out.println(day.toString()));
        return operatorDTO;
    }
    public void updateOperator(Operator operator,Integer id){
        Operator getOperator=operatorRepository.findById(id).get();
        getOperator.copyOperator(operator);
        operatorRepository.save(getOperator);
    }
    public void addInvoice(Operator operator,Integer id) {
        Operator getOperator=operatorRepository.findById(id).get();
        getOperator.addInvoice(operator.getInvoices().get(0));
        operatorRepository.save(getOperator);
    }
    /////////Convert to  DTO
    public OperatorTableDTO convertToOperatorTableDTO(Operator operator){
        OperatorTableDTO operatorTableDTO=new OperatorTableDTO();
        operatorTableDTO.setId(operator.getId());
        operatorTableDTO.setEmail(operator.getEmail());
        operatorTableDTO.setImg(operator.getAvatar());
        operatorTableDTO.setNickname(operator.getNickname());
        operatorTableDTO.setName(operator.getName());
        operatorTableDTO.setSurname(operator.getSurname());
        operatorTableDTO.setStatus(operator.getStatus());
        operatorTableDTO.setTel(operator.getPhone());

        return operatorTableDTO;
    }

    public ResponseEntity<?> setWorkedHours(Integer id, String date, Integer workedHours) {
        try {
            Date today=new Date();
            Date newDate = new SimpleDateFormat("dd.MM.yyyy").parse(date);
            System.out.println("id=" + id);
            System.out.println("date=" + newDate);
            System.out.println("workedHours=" + workedHours);
           System.out.println(CompareDateDMY.compareDates(today,newDate));

            Optional<Operator> optionalOperator=operatorRepository.findById(id);
            if(!optionalOperator.isPresent()) return  ResponseEntity.badRequest().body("Operatorul nu exista");
            Operator operator=optionalOperator.get();
            List<OperatorWorkDays> operatorWorkDays=operator.getOperatorWorkDays();
            OperatorWorkDays foundDay=findDay(operatorWorkDays,newDate );
            System.out.println("foundDate=" + foundDay);
            if(foundDay==null){
                operatorWorkDays.add(new OperatorWorkDays(newDate,workedHours));
            }
            else {
                foundDay.setWorkedHours(workedHours);
            }
            operator.setOperatorWorkDays(operatorWorkDays);
            operatorRepository.save(operator);
//            operatorWorkDays.add(new OperatorWorkDays(newDate,workedHours));
//            operator.setOperatorWorkDays(operator.);
            return ResponseEntity.ok().body("datele au fost adaugate");
        }catch(ParseException e){
            return ResponseEntity.badRequest().body("Data nu e in format corect");
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("An error occurred: " + e.getMessage());
        }
    }
    public List<WeeklySalesDTO> getSales(Integer id,Integer period){
        Calendar currentDate = Calendar.getInstance();
        Calendar monthsAgo = Calendar.getInstance();
        monthsAgo.add(Calendar.MONTH, -period);
        Date startDate=monthsAgo.getTime();
        List<WeeklySalesDTO> salesDTOS=operatorRepository.getWeeklySales(id,startDate);
        List<WeeklySalesDTO> weeksList=new ArrayList<>();
        while(currentDate.after(monthsAgo)){
            Calendar weekStart = (Calendar) currentDate.clone();
            weekStart.set(Calendar.DAY_OF_WEEK, Calendar.MONDAY);
            weekStart.set(Calendar.HOUR_OF_DAY, 0);
            weekStart.set(Calendar.MINUTE, 0);
            weekStart.set(Calendar.SECOND, 0);
            Calendar weekEnd = (Calendar) weekStart.clone();
            weekEnd.add(Calendar.DAY_OF_WEEK, 6);
            double sumSales = FindSumForDate.findSumForDate(salesDTOS, weekStart.getTime(),weekEnd.getTime());
            weeksList.add(new WeeklySalesDTO(weekStart.getTime(),sumSales));
            currentDate.add(Calendar.WEEK_OF_YEAR, -1);
        }
        return weeksList;
    }

    public String addOperator(
                             String nickname,
                              String name,
                              String surname,
                              String email,
                              Integer phone,
                              String address,
                              String imgName,
                              MultipartFile file) {
        try {
//            System.out.println(nickname + imgName + phone + address + email);
            ImageHandler imageHandler = new ImageHandler();
            byte[] bytes = file.getBytes();
            if(imgName.isEmpty()){
                imgName="avatar.jpg";
            } else{
                imgName=imageHandler.setImgName(imgName, folder);
                String filePath = folder + imgName;
                Files.write(Paths.get(filePath), bytes);
            }


            String dbFilePath = "/img/operators/" + imgName;
//            String filePath = folder + imgName;
//            Files.write(Paths.get(filePath), bytes);
            Operator operator = new Operator(nickname,
                    "1111",
                    dbFilePath,
                    email.isEmpty() ? "--" : email,
                    phone,
                    address.isEmpty() ? "--" : address,
                    name,surname,"activ");
            operatorRepository.save(operator);
            return "Operatorul a fost creat cu succes.";
        }catch (Exception e){
            return "An error occurred: " + e.getMessage();
        }

    }

    public String updateOperator(Integer id, String nickname, String name, String surname, String email, Integer phone, String address, String imgName, MultipartFile file)  {
       try{
        Optional<Operator> optionalOperator=operatorRepository.findById(id);
        if(!optionalOperator.isPresent()) return "Operatorul nu a fost gasit";
        Operator operator=optionalOperator.get();
        String originalImgName=operator.getAvatar().substring(operator.getAvatar().lastIndexOf('/')+1);
        File fileImg=new File(folder+originalImgName);
        String dbFilePath="/img/operators/"+originalImgName;
        String filePath;
        ImageHandler imageHandler=new ImageHandler();
        if(file.isEmpty()){
            if(!originalImgName.equals(imgName) && !originalImgName.equals("avatar.jpg")){
                String newImgName=imageHandler.setImgName(imgName,folder);
                File newFile=new File(folder+newImgName);
                fileImg.renameTo(newFile);
                dbFilePath="/img/operators/"+newImgName;
                System.out.println("Primul"+newImgName);
            }
        }else{
            String newImgName=imageHandler.setImgName(imgName,folder);
            byte[] bytes=file.getBytes();
            filePath=folder+newImgName;
            Files.write(Paths.get(filePath), bytes);
            System.out.println("aL DOILEA"+newImgName);
            boolean areEqual= CompareFiles.compareFiles(avatarImage,fileImg);
            if(!areEqual){
                System.out.println("areEqual="+areEqual);
                System.out.println("avatarImage"+avatarImage.getAbsolutePath());
                System.out.println("fileImg"+fileImg.getAbsolutePath());
                if(!fileImg.delete()) return "Probleme la stergera imaginii vechi";
            }

            dbFilePath="/img/operators/"+newImgName;
        }
            operator.setName(name);
            operator.setNickname(nickname);
            operator.setSurname(surname);
            operator.setPhone(phone);
            operator.setAddress(address);
            operator.setAvatar(dbFilePath);
            operatorRepository.save(operator);


        return "Operatorul a fost editat cu succes";
    }catch (Exception e){
        return "An error occurred: " + e.getMessage();
    }
    }

    public String deleteOperator(Integer id) {
        try{
            Optional<Operator> optionalOperator=operatorRepository.findById(id);
            if(!optionalOperator.isPresent()) return "Operatorul nu a fost gasit.";
            Operator operator=optionalOperator.get();
            String originalImgName=operator.getAvatar().substring(operator.getAvatar().lastIndexOf('/')+1);
            File fileImg=new File(folder+originalImgName);
            boolean areEqual= CompareFiles.compareFiles(avatarImage,fileImg);
            if(!areEqual){
                if(!fileImg.delete()) return "Probleme la stergera imaginii vechi";
                operator.setAvatar("/img/operators/avatar.jpg");
            }
            operator.setStatus("inactiv");
            operatorRepository.save(operator);
            return "Operatorul a fost șters";

        }catch (Exception e) {
            return "An error occurred: " + e.getMessage();
        }
    }
}
