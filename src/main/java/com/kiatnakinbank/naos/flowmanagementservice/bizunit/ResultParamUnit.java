package com.kiatnakinbank.naos.flowmanagementservice.bizunit;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kiatnakinbank.naos.common.framework.enums.ActiveFlag;
import com.kiatnakinbank.naos.flowmanagementservice.dto.ResultParamDto;
import com.kiatnakinbank.naos.flowmanagementservice.entity.TbMResultParamEntity;
import com.kiatnakinbank.naos.flowmanagementservice.repository.TbMFlowRepository;
import com.kiatnakinbank.naos.flowmanagementservice.repository.TbMResultParamRepository;

@Service
public class ResultParamUnit {
    private static final Logger LOGGER = LoggerFactory.getLogger(FlowUnit.class);

    @Autowired
    private TbMResultParamRepository tbMResultParamRepository;

    @Autowired
    private TbMFlowRepository tbMFlowRepository;

    public List<ResultParamDto> getResultParamByCondition(String resultName) {
        LOGGER.info("============ ResultParamUnit getResultParamByCondition ============");
        List<TbMResultParamEntity> tbMResultParamEntityList = new ArrayList<>();
        if (resultName == null || resultName.isEmpty()) {
            tbMResultParamEntityList = tbMResultParamRepository.findAll();
        } else {
            tbMResultParamEntityList = tbMResultParamRepository.findByResultParamNameContainingIgnoreCase(resultName);
        }
        return this.mapTbMResultParamListToResultParamDto(tbMResultParamEntityList);
    }

    public Boolean duplicateResultParamCode(String resultParamCode) {
        return tbMResultParamRepository.countByResultParamCode(resultParamCode) > 0 ? Boolean.TRUE : Boolean.FALSE;
    }

    public Boolean checkResultParamUsing(String resultParamCode) {
        return tbMFlowRepository.countByFlowResultParam(resultParamCode) > 0 ? Boolean.TRUE : Boolean.FALSE;
    }

    public TbMResultParamEntity saveTbMResultParamEntity(TbMResultParamEntity tbMResultParamEntity) {
        return tbMResultParamRepository.save(tbMResultParamEntity);
    }

    private List<ResultParamDto> mapTbMResultParamListToResultParamDto(
            List<TbMResultParamEntity> tbMResultParamEntityList) {
        List<ResultParamDto> resultParamDtoList = new ArrayList<>();
        for (TbMResultParamEntity row : tbMResultParamEntityList) {
            ResultParamDto resultParamDto = new ResultParamDto();
            resultParamDto.setResultParamCode(row.getResultParamCode());
            resultParamDto.setResultParamName(row.getResultParamName());
            resultParamDto.setResultParamType(row.getResultParamType());
            resultParamDto.setIsActive(row.getIsActive());
            resultParamDto.setCreateDate(row.getCreateDate());
            resultParamDto.setCreateUser(row.getCreateUser());
            resultParamDto.setUpdateDate(row.getUpdateDate());
            resultParamDto.setUpdateUser(row.getUpdateUser());
            resultParamDtoList.add(resultParamDto);
        }
        return resultParamDtoList;
    }

    public boolean checkResultParamInactive(String resultParamCode) {
        return this.tbMResultParamRepository.findByResultParamCode(resultParamCode).get(0).getIsActive()
                .equals(ActiveFlag.N);
    }

    public void deleteResultParamByResultParamCode(String resultParamCode) {
        this.tbMResultParamRepository.deleteById(resultParamCode);
    }

    public TbMResultParamEntity getTbMResultParamById(String resultParamCode) {
        return this.tbMResultParamRepository.findByResultParamCode(resultParamCode).get(0);
    }
}
