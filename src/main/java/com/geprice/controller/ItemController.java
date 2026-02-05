package com.geprice.controller;

import com.geprice.Constants;
import com.geprice.Util;
import com.geprice.error.GEPrice404Error;
import com.geprice.pojo.*;
import com.geprice.repository.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping("/api/items")
public class ItemController {

    private static final Logger log = LoggerFactory.getLogger(ItemController.class);

    private final ItemRepo itemRepo;
    private final BossRepo bossRepo;
    private final BossItemRepo bossItemRepo;
    private final CategoryRepo categoryRepo;
    private final CategoryItemRepo categoryItemRepo;
    private final ItemDetailRepo itemDetailRepo;

    ItemController(ItemRepo itemRepo, BossRepo bossRepo, BossItemRepo bossItemRepo, CategoryRepo categoryRepo, CategoryItemRepo categoryItemRepo, ItemDetailRepo itemDetailRepo) {
        this.itemRepo = itemRepo;
        this.bossRepo = bossRepo;
        this.bossItemRepo = bossItemRepo;
        this.categoryRepo = categoryRepo;
        this.categoryItemRepo = categoryItemRepo;
        this.itemDetailRepo = itemDetailRepo;
    }

    @GetMapping("/all")
    public List<ItemSummaries> getAll() {
        return itemDetailRepo.findAll().stream()
                .map(item -> ItemSummaries.builder()
                                .id(item.getId())
                                .name(item.getName())
                                .categoryId(item.getCategoryId())
                                .currentWeekAverage(item.getCurrentWeekAverage())
                                .previousWeekAverage(item.getPreviousWeekAverage())
                                .weeklyChange(item.getCurrentWeekAverage() - item.getPreviousWeekAverage())
                                .weeklyChangePercent(Util.getPercentChange(item.getCurrentWeekAverage(), item.getPreviousWeekAverage()))
                        .build()
                ).toList();
    }

    @GetMapping("/{id}")
    public Item getItem(@PathVariable String id) {
        Optional<Item> item = itemRepo.findById(Integer.parseInt(id));
        if (item.isPresent()) {
            log.debug("Item {} with id {} found", item.get().getName(), id);
            return item.get();
        } else {
            log.warn("Item with id {} not found", id);
            throw new GEPrice404Error(Constants.ITEM_NOT_FOUND);
        }
    }

    @GetMapping("/search/{query}")
    public ItemsPaged getItems(@PathVariable String query,
                               @RequestParam(value = "pageSize", required = false, defaultValue = "20") String pageSize,
                               @RequestParam(value = "pageNumber", required = false, defaultValue = "0") String pageNumber) {
        List<Item> items = itemRepo.findAllByNameUpperContaining(query.toUpperCase(Locale.UK), PageRequest.of(Integer.parseInt(pageNumber),
                Integer.parseInt(pageSize),
                Sort.by("name").ascending()));
        return ItemsPaged.builder()
                .items(items)
                .query(query)
                .pageNumber(Integer.parseInt(pageNumber))
                .pageSize(Integer.parseInt(pageSize))
                .build();
    }

    @GetMapping("/boss/{bossId}")
    public BossItems getBoss(@PathVariable String bossId) {
        Optional<Boss> boss = bossRepo.findById(Integer.parseInt(bossId));
        if (boss.isEmpty()) {
            log.error("Boss {} not found", bossId);
            throw new GEPrice404Error("Boss not found");
        }
        BossItems.BossItemsBuilder bossItem = BossItems.builder();
        bossItem.boss(boss.get());

        List<BossItem> bossDropsRaw = bossItemRepo.findAllByBossId(Integer.parseInt(bossId));
        if (bossDropsRaw.isEmpty()) {
            log.warn("No items found for boss {}", bossId);
        }

        List<Item> bossDropItems = new ArrayList<>();
        for (BossItem bossDrop : bossDropsRaw) {
            Optional<Item> item = itemRepo.findById(bossDrop.getItemId());
            if (item.isPresent()) {
                log.debug("Item {} with id {} found for boss {}", item.get().getName(), bossDrop.getItemId(), bossDrop.getBossId());
                bossDropItems.add(item.get());
            } else {
                log.warn("Item with id {} not found for boss {}", bossDrop.getItemId(), bossDrop.getBossId());
            }
        }

        bossItem.items(bossDropItems);
        return bossItem.build();
    }

    @GetMapping("/category/{categoryId}")
    public CategoryItems getCategory(@PathVariable String categoryId) {
        Optional<Category> category = categoryRepo.findById(Integer.parseInt(categoryId));
        if (category.isEmpty()) {
            log.error("Category {} not found", categoryId);
            throw new GEPrice404Error("Category not found");
        }
        CategoryItems.CategoryItemsBuilder categoryItems = CategoryItems.builder();
        categoryItems.category(category.get());

        List<CategoryItem> categoryItemsRaw = categoryItemRepo.findAllByCategoryId(Integer.parseInt(categoryId));
        if (categoryItemsRaw.isEmpty()) {
            log.warn("No items found for category {}", categoryId);
        }

        List<Item> categoryDropItems = new ArrayList<>();
        for (CategoryItem categoryItem : categoryItemsRaw) {
            Optional<Item> item = itemRepo.findById(categoryItem.getItemId());
            if (item.isPresent()) {
                log.debug("Item {} with id {} found for category {}", item.get().getName(), categoryItem.getItemId(), categoryId);
                categoryDropItems.add(item.get());
            } else {
                log.warn("Item with id {} not found for category {}", categoryItem.getItemId(), categoryId);
            }
        }

        categoryItems.items(categoryDropItems);
        return categoryItems.build();
    }
}
