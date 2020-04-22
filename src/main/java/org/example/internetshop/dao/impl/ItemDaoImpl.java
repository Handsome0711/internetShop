package org.example.internetshop.dao.impl;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;
import org.example.internetshop.dao.ItemDao;
import org.example.internetshop.dao.Storage;
import org.example.internetshop.lib.Dao;
import org.example.internetshop.model.Item;

@Dao
public class ItemDaoImpl implements ItemDao {

    @Override
    public Item create(Item item) {
        Storage.addItemToList(item);
        return item;
    }

    @Override
    public Optional<Item> get(Long id) {
        return Storage.items
                .stream()
                .filter(item -> item.getId().equals(id))
                .findFirst();
    }

    @Override
    public List<Item> getAll() {
        return Storage.items;
    }

    @Override
    public Item update(Item item) {
        Long id = Storage.items
                .stream()
                .filter(i -> i.equals(item))
                .map(Item::getId)
                .findFirst()
                .orElseThrow(() ->
                        new NoSuchElementException("Can't find item for update"));
        Storage.items.set(id.intValue() - 1, item);
        return item;
    }

    @Override
    public boolean delete(Long id) {
        return Storage.items.remove(id.intValue() - 1) != null;
    }
}
