package com.kiatnakinbank.naos.flowmanagementservice.bizunit;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kiatnakinbank.naos.common.framework.enums.ActiveFlag;
import com.kiatnakinbank.naos.flowmanagementservice.dto.dropdown.DropdownResponse;
import com.kiatnakinbank.naos.flowmanagementservice.entity.TbMDecisionEntity;
import com.kiatnakinbank.naos.flowmanagementservice.entity.TbMUniversalFieldNewEntity;
import com.kiatnakinbank.naos.flowmanagementservice.repository.TbMDecisionRepository;
import com.kiatnakinbank.naos.flowmanagementservice.repository.TbMUniversalFieldNewRepository;

@Service
public class DropdownUnit {

    private static final Logger LOGGER = LoggerFactory.getLogger(DropdownUnit.class);


    @Autowired
    private TbMDecisionRepository tbMDecisionRepository;

    @Autowired
    private TbMUniversalFieldNewRepository tbMUniversalFieldNewRepository;

    public List<DropdownResponse> getSubFlowList(ActiveFlag flagShowCode) {
        LOGGER.info("============ DropdownUnit getSubFlowList ============");
        List<DropdownResponse> dropdownResponses = new ArrayList<>();
        for (TbMDecisionEntity row : this.tbMDecisionRepository.findAllByOrderByDecisionCodeAsc()) {
            dropdownResponses.add(new DropdownResponse(String.valueOf(row.getDecisionCode()),
                    (flagShowCode != null && flagShowCode.equals(ActiveFlag.Y))
                            ? row.getDecisionCode() + " : " + row.getDecisionName()
                            : row.getDecisionName(),
                    row));
        }
        return dropdownResponses;
    }

    public List<DropdownResponse> getUniversalFieldList(ActiveFlag flagShowCode, String universalType) {
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
