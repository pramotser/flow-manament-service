package com.kiatnakinbank.naos.flowmanagementservice.bizunit;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kiatnakinbank.naos.common.framework.enums.ActiveFlag;
import com.kiatnakinbank.naos.flowmanagementservice.dto.UniversalFieldDto;
import com.kiatnakinbank.naos.flowmanagementservice.entity.TbMUniversalFieldNewEntity;
import com.kiatnakinbank.naos.flowmanagementservice.repository.TbMDecisionRepository;
import com.kiatnakinbank.naos.flowmanagementservice.repository.TbMUniversalFieldNewRepository;

@Service
public class UniversalUnit {
    private static final Logger LOGGER = LoggerFactory.getLogger(FlowUnit.class);

    @Autowired
    private TbMUniversalFieldNewRepository tbMUniversalFieldNewRepository;

    @Autowired
    private TbMDecisionRepository tbMDecisionRepository;

    public List<UniversalFieldDto> getUniversalListByCondition(String universalName) {
        LOGGER.info("============ UniversalUnit getUniversalListByCondition ============");
        return this.mapTbMUniversalFieldListToUniversalFieldDto(
                (universalName == null || universalName.isEmpty()) ? tbMUniversalFieldNewRepository.findAll()
                        : tbMUniversalFieldNewRepository.findByUniversalNameContainingIgnoreCase(universalName));
    }

    private List<UniversalFieldDto> mapTbMUniversalFieldListToUniversalFieldDto(List<TbMUniversalFieldNewEntity> list) {
        List<UniversalFieldDto> resultList = new ArrayList<>();
        for (TbMUniversalFieldNewEntity row : list) {
            UniversalFieldDto universalFieldDto = new UniversalFieldDto();
            universalFieldDto.setUniversalCode(row.getUniversalCode());
            universalFieldDto.setUniversalName(row.getUniversalName());
            universalFieldDto.setUniversalType(row.getUniversalType());
            universalFieldDto.setFieldType(row.getFieldType());
            universalFieldDto.setIsActive(row.getIsActive());
            universalFieldDto.setCreateDate(row.getCreateDate());
            universalFieldDto.setCreateUser(row.getCreateUser());
            universalFieldDto.setUpdateDate(row.getUpdateDate());
            universalFieldDto.setUpdateUser(row.getUpdateUser());
            resultList.add(universalFieldDto);
        }
        return resultList;
    }

    public Boolean checkDuplicateUniversalCode(String universalCode) {
        return tbMUniversalFieldNewRepository.countByUniversalCode(universalCode) > 0 ? Boolean.TRUE : Boolean.FALSE;
    }

    public TbMUniversalFieldNewEntity saveTbMUniversalFieldEntity(
            TbMUniversalFieldNewEntity tbMUniversalFieldNewEntity) {
        return tbMUniversalFieldNewRepository.save(tbMUniversalFieldNewEntity);
    }

    public TbMUniversalFieldNewEntity getTbMUniversalFieldById(String universalCode) {
        return this.tbMUniversalFieldNewRepository.findByUniversalCode(universalCode).get(0);
    }

    public boolean checkUniversalUsing(String universalCode) {
        return tbMDecisionRepository.countByDecisionResult(universalCode) > 0 ? Boolean.TRUE : Boolean.FALSE;
    }

    public boolean checkDuplicateUniversalFieldInactive(String universalCode) {
        return this.tbMUniversalFieldNewRepository.findByUniversalCode(universalCode).get(0).getIsActive()
                .equals(ActiveFlag.N);
    }

    public void deleteUniversalFieldByUniversalCode(String universalCode) {
        this.tbMUniversalFieldNewRepository.deleteById(universalCode);
    }
}