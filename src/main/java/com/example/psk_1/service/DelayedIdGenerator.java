package com.example.psk_1.service;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ExecutionException;

@ApplicationScoped
public class DelayedIdGenerator {
    @Inject
    private IdGenerator idGenerator;

    private Map<String, CompletableFuture<Integer>> tasks = new ConcurrentHashMap<>();

    public UUID startGeneration() {
        UUID uuid = UUID.randomUUID();
        CompletableFuture<Integer> future = CompletableFuture.supplyAsync(() -> {
            try {
                Thread.sleep(8000);
            } catch (InterruptedException ignored) {}

            return idGenerator.generate();
        });
        tasks.put(uuid.toString(), future);

        return uuid;
    }

    public String getItem(String uuid) throws ExecutionException, InterruptedException {
        CompletableFuture<Integer> futureItem = tasks.get(uuid);

        if (futureItem == null) {
            return null;
        } else if (!futureItem.isDone()) {
            return "Item is still generating";
        }

        tasks.remove(uuid);
        return futureItem.get().toString();
    }
}
