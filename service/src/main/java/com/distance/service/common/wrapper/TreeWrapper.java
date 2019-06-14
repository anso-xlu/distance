package com.distance.service.common.wrapper;

import com.distance.service.common.model.Tree;

import java.io.Serializable;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * model Tree
 */
public class TreeWrapper {
    /**
     * 转成tree对象
     */
    public static <T extends Tree, ID extends Serializable> List<T> asTree(List<T> list, ID parentId) {
        // 根据parentId分类
        Map<Serializable, List<T>> map = list.stream()
                .collect(Collectors.groupingBy(k ->
                        k.getParentId() == null ? 0 : k.getParentId()));
        // 获取元素的childes, 并排序
        list.forEach(e -> {
            if (map.containsKey(e.getId()))
                e.setChildren(map.get(e.getId())
                        .stream().sorted(Comparator.comparing(
                                vo -> vo.getSort() != null ? vo.getSort() : Integer.MAX_VALUE
                        )).collect(Collectors.toList()));
        });
        // 获取prentId 相同的元素,并排序
        return list.stream()
                .filter(t -> t.getParentId().equals(parentId == null ? 0 : parentId))
                .sorted(Comparator.comparing(
                        vo -> vo.getSort() != null ? vo.getSort() : Integer.MAX_VALUE
                )).collect(Collectors.toList());
    }

    /**
     * 超类下的所有子类
     *
     * @return
     */
    public static <T extends Tree, ID extends Serializable> Map<ID, List<T>> asAllChild(List<T> list) {
        //根据parentId分类
        Map<Serializable, List<T>> map = list.stream()
                .collect(Collectors.groupingBy(k ->
                        k.getParentId() == null ? 0 : k.getParentId()));

        //转成idMap
        Map<Serializable, T> idMap = list.stream()
                .collect(Collectors.toMap(T::getId, Function.identity()));

        //循环获取所有子类
        map.forEach((k, v) -> {
            T vo = idMap.get(k);
            while (vo.getParentId() != null && !vo.getParentId().equals(0)) {
                map.get(vo.getParentId()).addAll(v);
                vo = idMap.get(vo.getParentId());
            }
            //过滤重复
            map.put(k, map.get(k).stream()
                    .distinct().collect(Collectors.toList()));
        });
        return (Map<ID, List<T>>) map;
    }
}
