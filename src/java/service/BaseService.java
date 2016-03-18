/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import entity.BaseEntity;
import java.util.ArrayList;
import java.util.List;
import vo.BaseVO;

/**
 *
 * @author andrestntx
 */
public class BaseService {
    
    protected List<BaseVO> toVO(List<BaseEntity> entities) {
        List<BaseVO> vos = new ArrayList<>();
        for (BaseEntity entity : entities) {
            vos.add(entity.toVO());
        }
        return vos;
    }
    
    protected List<BaseEntity> toEntity(List<BaseVO> vos) {
        List<BaseEntity> entities = new ArrayList<>();
        for (BaseVO vo : vos) {
            entities.add(vo.toEntity());
        }
        return entities;
    }
}
