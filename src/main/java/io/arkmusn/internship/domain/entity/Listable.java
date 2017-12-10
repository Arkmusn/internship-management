package io.arkmusn.internship.domain.entity;

/**
 * @author Arkmusn on 2017/12/10.
 */
public interface Listable {
    public int getPage();

    public int getSize();

    public void setPage(int page);

    public void setSize(int size);
}
