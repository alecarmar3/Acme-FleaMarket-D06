
    alter table `acme_item` 
       drop 
       foreign key `FKd9dijpvw3tp85ikr8dtyyedv0`;

    alter table `acme_item_request` 
       drop 
       foreign key `FKk15moed4s706hd29yu0prtj3d`;

    alter table `acme_item_request` 
       drop 
       foreign key `FKti0at6h85rrr7epivt1plqkw1`;

    alter table `administrator` 
       drop 
       foreign key FK_2a5vcjo3stlfcwadosjfq49l1;

    alter table `anonymous` 
       drop 
       foreign key FK_6lnbc6fo3om54vugoh8icg78m;

    alter table `authenticated` 
       drop 
       foreign key FK_h52w0f3wjoi68b63wv9vwon57;

    alter table `buyer` 
       drop 
       foreign key FK_630a954if6nal5afofvjy73ob;

    alter table `specification_sheet` 
       drop 
       foreign key `FKsxisb81nhklkj6ga99chd3rei`;

    alter table `supplier` 
       drop 
       foreign key FK_1h83guf8tf3di74bk4uhuo1ia;

    drop table if exists `acme_item`;

    drop table if exists `acme_item_request`;

    drop table if exists `administrator`;

    drop table if exists `advertisement`;

    drop table if exists `anonymous`;

    drop table if exists `authenticated`;

    drop table if exists `buyer`;

    drop table if exists `configuration`;

    drop table if exists `figment`;

    drop table if exists `material_sheet`;

    drop table if exists `news`;

    drop table if exists `sheet`;

    drop table if exists `specification_sheet`;

    drop table if exists `suggestion`;

    drop table if exists `supplier`;

    drop table if exists `tool_sheet`;

    drop table if exists `user_account`;

    drop table if exists `hibernate_sequence`;
