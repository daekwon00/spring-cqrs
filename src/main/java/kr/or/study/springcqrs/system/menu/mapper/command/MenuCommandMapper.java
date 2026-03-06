package kr.or.study.springcqrs.system.menu.mapper.command;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface MenuCommandMapper {

    void insertMenu(@Param("menuId") String menuId, @Param("menuName") String menuName,
                    @Param("menuUrl") String menuUrl, @Param("menuIcon") String menuIcon,
                    @Param("parentMenuId") String parentMenuId, @Param("sortOrder") int sortOrder,
                    @Param("createdBy") String createdBy);

    void updateMenu(@Param("menuId") String menuId, @Param("menuName") String menuName,
                    @Param("menuUrl") String menuUrl, @Param("menuIcon") String menuIcon,
                    @Param("parentMenuId") String parentMenuId, @Param("sortOrder") int sortOrder,
                    @Param("modifiedBy") String modifiedBy);

    void deleteMenu(@Param("menuId") String menuId);
}
