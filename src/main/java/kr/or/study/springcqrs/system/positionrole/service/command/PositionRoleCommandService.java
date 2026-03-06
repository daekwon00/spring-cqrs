package kr.or.study.springcqrs.system.positionrole.service.command;

import kr.or.study.springcqrs.system.positionrole.mapper.command.PositionRoleCommandMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(rollbackFor = Exception.class)
public class PositionRoleCommandService {

    private final PositionRoleCommandMapper positionRoleCommandMapper;

    public void updatePositionRoles(String positionId, List<String> roleIds, String userId) {
        positionRoleCommandMapper.deleteByPositionId(positionId);
        for (String roleId : roleIds) {
            positionRoleCommandMapper.insertPositionRole(positionId, roleId, userId);
        }
    }
}
