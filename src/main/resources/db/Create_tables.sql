CREATE TABLE "purchases" (
    "id" int,
    "name" text not null,
    CONSTRAINT "pk_purchase_id" PRIMARY KEY(id)
);

CREATE TABLE "purchase_info" (
    "id" int,
    "name" text not null,
	"lastname" text not null,
	"age" int,
	"purchase_item" int not null,
	"count" int not null,
	"amount" numeric not null check (amount > 0),
	"purchase_date" timestamp not null,
	"user_id" int not null,
    CONSTRAINT "pk_purchase_info_id" PRIMARY KEY(id),
	CONSTRAINT "fk_purchase_info_purchase_item_id" FOREIGN KEY("purchase_item")
    	REFERENCES "purchases" ("id"),
    CONSTRAINT "fk_purchases_info_user_id" FOREIGN KEY("user_id")
        REFERENCES "users" ("id")
);