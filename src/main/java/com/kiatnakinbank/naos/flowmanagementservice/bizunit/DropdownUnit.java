package com.kiatnakinbank.naos.flowmanagementservice.bizunit;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kiatnakinbank.naos.common.framework.enums.ActiveFlag;
import com.kiatnakinbank.naos.flowmanagementservice.constants.Constants;
import com.kiatnakinbank.naos.flowmanagementservice.dto.dropdown.DropdownResponse;
import com.kiatnakinbank.naos.flowmanagementservice.entity.TbMResultParamEntity;
import com.kiatnakinbank.naos.flowmanagementservice.entity.TbMUniversalFieldEntity;
import com.kiatnakinbank.naos.flowmanagementservice.entity.TbMUniversalFieldNewEntity;
import com.kiatnakinbank.naos.flowmanagementservice.entity.TbMFlowEntity;
import com.kiatnakinbank.naos.flowmanagementservice.repository.TbMFlowRepository;
import com.kiatnakinbank.naos.flowmanagementservice.repository.TbMResultParamRepository;
import com.kiatnakinbank.naos.flowmanagementservice.repository.TbMUniversalFieldNewRepository;
import com.kiatnakinbank.naos.flowmanagementservice.repository.TbMUniversalFieldRepository;

@Service
public class DropdownUnit {

    private static final Logger LOGGER = LoggerFactory.getLogger(DropdownUnit.class);
    @Autowired
    private TbMFlowRepository tbMFlowRepository;

    @Autowired
    private TbMResultParamRepository tbMResultParamRepository;

    @Autowired
    private TbMUniversalFieldRepository tbMUniversalFieldRepository;

    @Autowired
    private TbMUniversalFieldNewRepository tbMUniversalFieldNewRepository;

    public List<DropdownResponse> getFlowList(ActiveFlag flagShowCode) {
        LOGGER.info("============ DropdownUnit getFlowList ============");
        List<DropdownResponse> dropdownResponses = new ArrayList<>();
        for (TbMFlowEntity row : this.tbMFlowRepository.findAllByOrderByFlowIdAsc()) {
            dropdownResponses.add(new DropdownResponse(String.valueOf(row.getFlowId()),
                    (flagShowCode != null && flagShowCode.equals(ActiveFlag.Y))
                            ? row.getFlowId() + " : " + row.getFlowName()
                            : row.getFlowName(),
                    row));
        }
        return dropdownResponses;
    }

    public List<DropdownResponse> getResultParamList(ActiveFlag flagShowCode) {
        LOGGER.info("============ DropdownUnit getResultParamList ============");
        List<DropdownResponse> dropdownResponses = new ArrayList<>();
        for (TbMResultParamEntity row : this.tbMResultParamRepository.findAllByOrderByResultParamCodeAsc()) {
            dropdownResponses.add(new DropdownResponse(row.getResultParamCode(),
                    (flagShowCode != null && flagShowCode.equals(ActiveFlag.Y))
                            ? row.getResultParamCode() + " : " + row.getResultParamName()
                            : row.getResultParamName(),
                    row));
        }
        return dropdownResponses;
    }

    // public List<DropdownResponse> getUniversalFieldList(ActiveFlag flagShowCode) {
    //     LOGGER.info("============ DropdownUnit getUniversalFieldList ============");
    //     List<DropdownResponse> dropdownResponses = new ArrayList<>();
    //     for (TbMUniversalFieldEntity row : this.tbMUniversalFieldRepository.findAll()) {
    //         dropdownResponses.add(new DropdownResponse(row.getUniversalFieldCode(),
    //                 (flagShowCode != null && flagShowCode.equals(ActiveFlag.Y))
    //                         ? row.getUniversalFieldCode() + " : " + row.getUniversalFieldName()
    //                         : row.getUniversalFieldName(),
    //                 row));
    //     }
    //     return dropdownResponses;
    // }

    public List<DropdownResponse> getUniversalFieldList(ActiveFlag flagShowCode , String universalType) {
        LOGGER.info("============ DropdownUnit getUniversalFieldNewList ============");
        List<DropdownResponse> dropdownResponses = new ArrayList<>();
        for (TbMUniversalFieldNewEntity row : this.tbMUniversalFieldNewRepository.findByUniversalType(universalType)) {
            dropdownResponses.add(new DropdownResponse(row.getUniversalCode(),
                    (flagShowCode != null && flagShowCode.equals(ActiveFlag.Y))
                            ? row.getUniversalCode() + " : " + row.getUniversalName()
                            : row.getUniversalName(),
                    row));
        }
        return dropdownResponses;
    }
}
