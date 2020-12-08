
    create table `acme_item` (
       `id` integer not null,
        `version` integer not null,
        `additional_information` varchar(255),
        `category` varchar(255),
        `creation_date` datetime(6),
        `description` varchar(255),
        `final_mode` bit,
        `is_new` bit,
        `photo` varchar(255),
        `price_amount` double precision,
        `price_currency` varchar(255),
        `ticker` varchar(255),
        `title` varchar(255),
        `update_date` datetime(6),
        `supplier_id` integer not null,
        primary key (`id`)
    ) engine=InnoDB;

    create table `acme_item_request` (
       `id` integer not null,
        `version` integer not null,
        `creation_date` datetime(6),
        `justification` varchar(255),
        `notes` varchar(255),
        `quantity` integer,
        `status` integer,
        `ticker` varchar(255),
        `acme_item_id` integer not null,
        `buyer_id` integer not null,
        primary key (`id`)
    ) engine=InnoDB;

    create table `administrator` (
       `id` integer not null,
        `version` integer not null,
        `user_account_id` integer,
        primary key (`id`)
    ) engine=InnoDB;

    create table `advertisement` (
       `id` integer not null,
        `version` integer not null,
        `average_interval` integer,
        `average_volume_discount` double precision,
        `creation_date` datetime(6),
        `displayed_until` datetime(6),
        `large_interval` integer,
        `large_volume_discount` double precision,
        `picture` varchar(255),
        `small_interval` integer,
        `small_volume_discount` double precision,
        `text_piece` varchar(255),
        `title` varchar(255),
        `update_date` datetime(6),
        primary key (`id`)
    ) engine=InnoDB;

    create table `anonymous` (
       `id` integer not null,
        `version` integer not null,
        `user_account_id` integer,
        primary key (`id`)
    ) engine=InnoDB;

    create table `authenticated` (
       `id` integer not null,
        `version` integer not null,
        `user_account_id` integer,
        primary key (`id`)
    ) engine=InnoDB;

    create table `buyer` (
       `id` integer not null,
        `version` integer not null,
        `user_account_id` integer,
        `brand` varchar(255),
        `credit_card_number` varchar(255),
        `cvv` varchar(255),
        `delivery_address` varchar(255),
        `email` varchar(255),
        `expiration_date` varchar(255),
        `holder_name` varchar(255),
        `phone_number` varchar(255),
        primary key (`id`)
    ) engine=InnoDB;

    create table `configuration` (
       `id` integer not null,
        `version` integer not null,
        `acme_item_categories` varchar(255),
        `news_categories` varchar(255),
        `spam_threshold` double precision,
        `spam_words` varchar(255),
        primary key (`id`)
    ) engine=InnoDB;

    create table `figment` (
       `id` integer not null,
        `version` integer not null,
        `creation_date` datetime(6),
        `inventor_name` varchar(255),
        `price_max_amount` double precision,
        `price_max_currency` varchar(255),
        `price_min_amount` double precision,
        `price_min_currency` varchar(255),
        `text_piece` varchar(255),
        `title` varchar(255),
        `update_date` datetime(6),
        primary key (`id`)
    ) engine=InnoDB;

    create table `material_sheet` (
       `id` integer not null,
        `version` integer not null,
        `description` varchar(255),
        `home_page` varchar(255),
        `provider_name` varchar(255),
        `stars` integer,
        `title` varchar(255),
        `update_date` datetime(6),
        primary key (`id`)
    ) engine=InnoDB;

    create table `news` (
       `id` integer not null,
        `version` integer not null,
        `body` varchar(255),
        `category` varchar(255),
        `creation_date` datetime(6),
        `deadline` datetime(6),
        `header_picture` varchar(255),
        `news_links` varchar(255),
        `title` varchar(255),
        primary key (`id`)
    ) engine=InnoDB;

    create table `sheet` (
       `id` integer not null,
        `version` integer not null,
        `description` varchar(255),
        `home_page` varchar(255),
        `provider_name` varchar(255),
        `stars` integer,
        `title` varchar(255),
        `update_date` datetime(6),
        primary key (`id`)
    ) engine=InnoDB;

    create table `specification_sheet` (
       `id` integer not null,
        `version` integer not null,
        `indexer` integer,
        `photo` varchar(255),
        `sheet_description` varchar(255),
        `sheet_title` varchar(255),
        `acme_item_id` integer not null,
        primary key (`id`)
    ) engine=InnoDB;

    create table `suggestion` (
       `id` integer not null,
        `version` integer not null,
        `creation_date` datetime(6),
        `email` varchar(255),
        `text_piece` varchar(255),
        `title` varchar(255),
        `update_date` datetime(6),
        primary key (`id`)
    ) engine=InnoDB;

    create table `supplier` (
       `id` integer not null,
        `version` integer not null,
        `user_account_id` integer,
        `acme_item_category` varchar(255),
        `company` varchar(255),
        `description` varchar(255),
        `home_page` varchar(255),
        primary key (`id`)
    ) engine=InnoDB;

    create table `tool_sheet` (
       `id` integer not null,
        `version` integer not null,
        `description` varchar(255),
        `home_page` varchar(255),
        `provider_name` varchar(255),
        `stars` integer,
        `title` varchar(255),
        `update_date` datetime(6),
        primary key (`id`)
    ) engine=InnoDB;

    create table `user_account` (
       `id` integer not null,
        `version` integer not null,
        `enabled` bit not null,
        `identity_email` varchar(255),
        `identity_name` varchar(255),
        `identity_surname` varchar(255),
        `password` varchar(255),
        `username` varchar(255),
        primary key (`id`)
    ) engine=InnoDB;

    create table `hibernate_sequence` (
       `next_val` bigint
    ) engine=InnoDB;

    insert into `hibernate_sequence` values ( 1 );
create index IDXmnox5ecej3werjujk8kxcnfb0 on `acme_item` (`final_mode`);
create index IDXpistaxqxs9wi9bg355n8rhl77 on `acme_item` (`supplier_id`);
create index IDXk9xf863x9koosi2u10i3eddxy on `acme_item` (`ticker`);

    alter table `acme_item` 
       add constraint UK_qmgye25wx96pq764xggwykrsr unique (`ticker`);
create index IDXfviyt6jwhwjnb2cbs6qft54o4 on `acme_item_request` (`ticker` asc);
create index IDX6omytt035d9q7ssqk620fgrmp on `acme_item_request` (`buyer_id`);
create index IDXuqdn8db4vfus11v3nup3lcau on `acme_item_request` (`acme_item_id`);

    alter table `acme_item_request` 
       add constraint UK_oby64nw8p7nau9qw0b43i4g2q unique (`ticker`);
create index IDXhnql3xtwsfihb8c757jb9ti2 on `advertisement` (`displayed_until`);
create index IDX8179mtglcgsrdvlbg3s35bimb on `buyer` (`user_account_id`);
create index IDX6a50xbt2tt8c9ki7dgsc6379q on `material_sheet` (`stars`);
create index IDXjfivymhh2fi7egrnx99uiejpx on `news` (`deadline`);
create index IDXqhte7jse2x0vkkr2qjt4tb0dl on `specification_sheet` (`acme_item_id`);
create index IDXhfqb13c8yymsky5fkj66g448 on `supplier` (`user_account_id`);
create index IDX6diph4y2lg9obtjhtn0e7ncvc on `tool_sheet` (`stars`);

    alter table `user_account` 
       add constraint UK_castjbvpeeus0r8lbpehiu0e4 unique (`username`);

    alter table `acme_item` 
       add constraint `FKd9dijpvw3tp85ikr8dtyyedv0` 
       foreign key (`supplier_id`) 
       references `supplier` (`id`);

    alter table `acme_item_request` 
       add constraint `FKk15moed4s706hd29yu0prtj3d` 
       foreign key (`acme_item_id`) 
       references `acme_item` (`id`);

    alter table `acme_item_request` 
       add constraint `FKti0at6h85rrr7epivt1plqkw1` 
       foreign key (`buyer_id`) 
       references `buyer` (`id`);

    alter table `administrator` 
       add constraint FK_2a5vcjo3stlfcwadosjfq49l1 
       foreign key (`user_account_id`) 
       references `user_account` (`id`);

    alter table `anonymous` 
       add constraint FK_6lnbc6fo3om54vugoh8icg78m 
       foreign key (`user_account_id`) 
       references `user_account` (`id`);

    alter table `authenticated` 
       add constraint FK_h52w0f3wjoi68b63wv9vwon57 
       foreign key (`user_account_id`) 
       references `user_account` (`id`);

    alter table `buyer` 
       add constraint FK_630a954if6nal5afofvjy73ob 
       foreign key (`user_account_id`) 
       references `user_account` (`id`);

    alter table `specification_sheet` 
       add constraint `FKsxisb81nhklkj6ga99chd3rei` 
       foreign key (`acme_item_id`) 
       references `acme_item` (`id`);

    alter table `supplier` 
       add constraint FK_1h83guf8tf3di74bk4uhuo1ia 
       foreign key (`user_account_id`) 
       references `user_account` (`id`);
