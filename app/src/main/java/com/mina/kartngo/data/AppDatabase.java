package com.mina.kartngo.data;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Database(entities = {ProductEntity.class}, version = 1, exportSchema = false)
public abstract class AppDatabase extends RoomDatabase {

    public abstract ProductDao productDao();

    private static volatile AppDatabase INSTANCE;
    private static final int NUMBER_OF_THREADS = 4;
    static final ExecutorService databaseWriteExecutor =
            Executors.newFixedThreadPool(NUMBER_OF_THREADS);

    public static AppDatabase getInstance(final Context context) {
        if (INSTANCE == null) {
            synchronized (AppDatabase.class) {
                if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                                    AppDatabase.class, "kartngo_database")
                            .addCallback(roomCallback)
                            .build();
                }
            }
        }
        return INSTANCE;
    }

    private static final RoomDatabase.Callback roomCallback = new RoomDatabase.Callback() {
        @Override
        public void onCreate(@NonNull SupportSQLiteDatabase db) {
            super.onCreate(db);
            databaseWriteExecutor.execute(() -> {
                ProductDao dao = INSTANCE.productDao();
                // Insert dummy products
                dao.insert(new ProductEntity("Apple iPhone 14", "https://example.com/iphone14.jpg", "Electronics", 999.99, "USD"));
                dao.insert(new ProductEntity("Nike Running Shoes", "https://example.com/nike_shoes.jpg", "Footwear", 120.00, "USD"));
                dao.insert(new ProductEntity("Coffee Mug", "https://example.com/mug.jpg", "Kitchen", 15.50, "USD"));
                dao.insert(new ProductEntity("Bluetooth Headphones", "https://example.com/headphones.jpg", "Electronics", 89.99, "USD"));
                dao.insert(new ProductEntity("Yoga Mat", "https://example.com/yogamat.jpg", "Fitness", 25.00, "USD"));
            });
        }
    };
}