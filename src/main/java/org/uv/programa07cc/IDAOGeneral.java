package org.uv.programa07cc;

import java.util.List;

/**
 *
 * @author zS20006736
 */
public interface IDAOGeneral<T, ID>{
    public T create(T v);
    public boolean delete(ID id);
    public T update(ID id, T vN);
    
    public List<T> findAll();
    public T findById(ID id);
}
