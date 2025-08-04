package com.mina.kartngo.data.local;

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
                dao.insert(new ProductEntity("Whopper", "https://w7.pngwing.com/pngs/520/119/png-transparent-french-fries-cheeseburger-breakfast-sandwich-whopper-hamburger-junk-food-food-recipe-cheese-sandwich-thumbnail.png", "Burger", 26.00, "AED", "ساندويتش لحم مشوي بحجم كبير مع خس وطماطم ومخلل وصوص الووبر."));
                dao.insert(new ProductEntity("Double Whopper", "https://w7.pngwing.com/pngs/520/119/png-transparent-french-fries-cheeseburger-breakfast-sandwich-whopper-hamburger-junk-food-food-recipe-cheese-sandwich-thumbnail.png", "Burger", 31.00, "AED", "برجر كبير بلحم مشوي مزدوج مع الجبنة والخضروات والصوص."));
                dao.insert(new ProductEntity("Chicken Royale", "https://w7.pngwing.com/pngs/520/119/png-transparent-french-fries-cheeseburger-breakfast-sandwich-whopper-hamburger-junk-food-food-recipe-cheese-sandwich-thumbnail.png", "Burger", 24.00, "AED", "دجاج مقلي مقرمش مع مايونيز وخس في خبز طويل."));
                dao.insert(new ProductEntity("Big King XXL", "https://w7.pngwing.com/pngs/520/119/png-transparent-french-fries-cheeseburger-breakfast-sandwich-whopper-hamburger-junk-food-food-recipe-cheese-sandwich-thumbnail.png", "Burger", 34.00, "AED", "برجر عملاق بلحم مشوي مزدوج مع جبنة وخس وطماطم."));
                dao.insert(new ProductEntity("Veggie Burger", "https://w7.pngwing.com/pngs/520/119/png-transparent-french-fries-cheeseburger-breakfast-sandwich-whopper-hamburger-junk-food-food-recipe-cheese-sandwich-thumbnail.png", "Burger", 22.00, "AED", "برجر نباتي مكون من خضروات طازجة وصوص مميز."));
                dao.insert(new ProductEntity("Crispy Chicken", "https://w7.pngwing.com/pngs/520/119/png-transparent-french-fries-cheeseburger-breakfast-sandwich-whopper-hamburger-junk-food-food-recipe-cheese-sandwich-thumbnail.png", "Burger", 20.00, "AED", "دجاج مقلي ومقرمش مع خس ومايونيز."));
                dao.insert(new ProductEntity("Fish Burger", "https://w7.pngwing.com/pngs/520/119/png-transparent-french-fries-cheeseburger-breakfast-sandwich-whopper-hamburger-junk-food-food-recipe-cheese-sandwich-thumbnail.png", "Burger", 21.00, "AED", "برجر سمك مقلي بصوص التارتار وخس."));
                dao.insert(new ProductEntity("Whopper Jr.", "https://w7.pngwing.com/pngs/520/119/png-transparent-french-fries-cheeseburger-breakfast-sandwich-whopper-hamburger-junk-food-food-recipe-cheese-sandwich-thumbnail.png", "Burger", 18.00, "AED", "نسخة مصغرة من الووبر بطعم مميز وخفيف."));
                dao.insert(new ProductEntity("Double Cheeseburger", "https://w7.pngwing.com/pngs/520/119/png-transparent-french-fries-cheeseburger-breakfast-sandwich-whopper-hamburger-junk-food-food-recipe-cheese-sandwich-thumbnail.png", "Burger", 22.00, "AED", "برجر بلحم مشوي مزدوج وشريحتين جبنة."));
                dao.insert(new ProductEntity("Cheeseburger", "https://w7.pngwing.com/pngs/520/119/png-transparent-french-fries-cheeseburger-breakfast-sandwich-whopper-hamburger-junk-food-food-recipe-cheese-sandwich-thumbnail.png", "Burger", 15.00, "AED", "برجر صغير بلحم وجبنة في خبز طازج."));

                dao.insert(new ProductEntity("Fries (Medium)", "https://w7.pngwing.com/pngs/838/978/png-transparent-potato-fries-french-fries-pizza-sushi-potato-hamburger-french-fries-food-cheeseburger-american-food-thumbnail.png", "Sides", 9.00, "AED", "بطاطس مقلية متوسطة الحجم مقرمشة."));
                dao.insert(new ProductEntity("Fries (Large)", "https://w7.pngwing.com/pngs/838/978/png-transparent-potato-fries-french-fries-pizza-sushi-potato-hamburger-french-fries-food-cheeseburger-american-food-thumbnail.png", "Sides", 11.00, "AED", "بطاطس مقلية كبيرة ومقرمشة."));
                dao.insert(new ProductEntity("Onion Rings", "https://w7.pngwing.com/pngs/838/978/png-transparent-potato-fries-french-fries-pizza-sushi-potato-hamburger-french-fries-food-cheeseburger-american-food-thumbnail.png", "Sides", 10.00, "AED", "حلقات بصل مقلية ذهبية ومقرمشة."));
                dao.insert(new ProductEntity("Chicken Nuggets (6 pcs)", "https://w7.pngwing.com/pngs/838/978/png-transparent-potato-fries-french-fries-pizza-sushi-potato-hamburger-french-fries-food-cheeseburger-american-food-thumbnail.png", "Sides", 14.00, "AED", "قطع دجاج مقرمش بعدد 6."));
                dao.insert(new ProductEntity("Mozzarella Sticks", "https://w7.pngwing.com/pngs/838/978/png-transparent-potato-fries-french-fries-pizza-sushi-potato-hamburger-french-fries-food-cheeseburger-american-food-thumbnail.png", "Sides", 15.00, "AED", "أصابع موزاريلا مقلية بالجبنة الذائبة."));

                dao.insert(new ProductEntity("Pepsi", "https://w7.pngwing.com/pngs/748/506/png-transparent-fizzy-drinks-energy-drink-pepsi-fast-food-drink-food-plastic-bottle-packaging-and-labeling-thumbnail.png", "Drinks", 8.00, "AED", "مشروب بيبسي بارد ومنعش."));
                dao.insert(new ProductEntity("7Up", "https://w7.pngwing.com/pngs/748/506/png-transparent-fizzy-drinks-energy-drink-pepsi-fast-food-drink-food-plastic-bottle-packaging-and-labeling-thumbnail.png", "Drinks", 8.00, "AED", "مشروب 7Up بطعم الليمون المنعش."));
                dao.insert(new ProductEntity("Mountain Dew", "https://w7.pngwing.com/pngs/748/506/png-transparent-fizzy-drinks-energy-drink-pepsi-fast-food-drink-food-plastic-bottle-packaging-and-labeling-thumbnail.png", "Drinks", 8.00, "AED", "مشروب غازي بنكهة الحمضيات."));
                dao.insert(new ProductEntity("Mirinda", "https://w7.pngwing.com/pngs/748/506/png-transparent-fizzy-drinks-energy-drink-pepsi-fast-food-drink-food-plastic-bottle-packaging-and-labeling-thumbnail.png", "Drinks", 8.00, "AED", "مشروب غازي بنكهة البرتقال."));
                dao.insert(new ProductEntity("Water", "https://w7.pngwing.com/pngs/748/506/png-transparent-fizzy-drinks-energy-drink-pepsi-fast-food-drink-food-plastic-bottle-packaging-and-labeling-thumbnail.png", "Drinks", 5.00, "AED", "مياه معدنية منعشة."));

                dao.insert(new ProductEntity("King Box Meal", "https://w7.pngwing.com/pngs/765/174/png-transparent-hamburger-veggie-burger-chicken-sandwich-kfc-french-fries-burger-king-food-recipe-fast-food-restaurant-thumbnail.png", "Meal", 35.00, "AED", "وجبة مشبعة تحتوي على ساندويتش، بطاطس ومشروب."));
                dao.insert(new ProductEntity("Family Meal", "https://w7.pngwing.com/pngs/765/174/png-transparent-hamburger-veggie-burger-chicken-sandwich-kfc-french-fries-burger-king-food-recipe-fast-food-restaurant-thumbnail.png", "Meal", 75.00, "AED", "وجبة عائلية تكفي حتى 4 أفراد."));
                dao.insert(new ProductEntity("Kids Meal", "https://w7.pngwing.com/pngs/765/174/png-transparent-hamburger-veggie-burger-chicken-sandwich-kfc-french-fries-burger-king-food-recipe-fast-food-restaurant-thumbnail.png", "Meal", 18.00, "AED", "وجبة للأطفال تشمل ساندويتش، بطاطس ولعبة."));


            });
        }
    };
}