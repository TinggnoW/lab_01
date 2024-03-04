package com.fsse2401.lab_b01;

import org.apache.coyote.Response;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
public class PersonApi {
    List<PersonData> personDataList = new ArrayList<>();


    @PostMapping("/person/create")
    public PersonData createPerson(@RequestBody PersonData personData){
        personDataList.add(personData);
        return personData;

    }

    @GetMapping("/person/all")
    public List<PersonData> getPersonDataList(){
        return personDataList;
    }
//    unable change
//    @PutMapping("/person/update")
//    public PersonData update (@RequestBody PersonData personData){
//       for(int i=0;i<personDataList.size();i++){
//           if(personDataList.get(i).getHkid().equals(personData.getHkid())){
//               personDataList.add(personData);
//           }
//       }return null;
//    }

    @PutMapping("/person/update")
    public ResponseEntity<PersonData> updatePersonByHKid (@RequestBody PersonData updatepersonData){
        for(PersonData  personData:personDataList){
            if(personData.getHkid().equals(updatepersonData.getHkid())){
                personData.setFirstName(updatepersonData.getFirstName());
                personData.setLastName(updatepersonData.getLastName());
                return new ResponseEntity<>(personData, HttpStatus.OK) ;
            }
        }return new ResponseEntity<>(updatepersonData, HttpStatus.BAD_REQUEST);
    }

    @DeleteMapping("/person/delete")
    public PersonData deletePerson (@RequestParam String hkid){
        for(PersonData  personData:personDataList){
            if(personData.getHkid().equals(hkid)){
                personDataList.remove(personData);
                return personData;
            }
        }return null;
    }

}



