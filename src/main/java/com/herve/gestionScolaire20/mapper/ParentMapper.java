package com.herve.gestionScolaire20.mapper;

import com.herve.gestionScolaire20.dto.ParentReqDTO;
import com.herve.gestionScolaire20.dto.ParentRespDTO;
import com.herve.gestionScolaire20.models.Parent;
import org.springframework.stereotype.Component;

@Component
public class ParentMapper {

    public Parent toEntity(ParentReqDTO parentReqDTO){

        Parent parent = new Parent();
        parent.setLastname(parentReqDTO.getLastname());
        parent.setFirstname(parentReqDTO.getFirstname());
        parent.setPhone(parentReqDTO.getPhone());
        parent.setEmail(parentReqDTO.getEmail());
        parent.setAddress(parentReqDTO.getAddress());

        return parent;
    }
    public ParentRespDTO toDto(Parent parent){
        ParentRespDTO parentRespDTO = new ParentRespDTO();
        parentRespDTO.setLastname(parent.getLastname());
        parentRespDTO.setFirstname(parent.getFirstname());
        parentRespDTO.setPhone(parent.getPhone());
        parentRespDTO.setEmail(parent.getEmail());
        parentRespDTO.setAddress(parent.getAddress());
        parentRespDTO.setParentId(parent.getParentId());

        return parentRespDTO;
    }
}
