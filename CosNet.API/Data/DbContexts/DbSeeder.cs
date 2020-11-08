﻿using System;
using System.Collections.Generic;
using System.Linq;
using CosNet.API.Entities;
using Microsoft.EntityFrameworkCore;

namespace CosNet.API.Data.DBContexts
{
    public static class DbSeeder
    {
        //Try keeping this file organized
        public static void Seed(ApplicationDbContext context)
        {
            if (!context.Cosplays.Any() && !context.CosplayItems.Any())
            {
                var cosplays = new List<Cosplay>()
                {
                    new Cosplay
                    {
                       CosplayId = Guid.NewGuid(),
                       Name = "Ichigo Kurosaki - Final Gestuga Tenshou",
                       Serie = "Bleach",
                       Budget= 100.0m,
                       StartDate = DateTime.Now,
                       DueDate = DateTime.Now.AddDays(40),
                       Status = "In Progress"
                    },
                    new Cosplay
                    {
                       CosplayId = Guid.NewGuid(),
                       Name = "Lelouch Lamperouge",
                       Serie = "Code Geass",
                       Budget= 90.0m,
                       StartDate = DateTime.Now.AddDays(2),
                       DueDate = DateTime.Now.AddDays(50),
                       Status = "Planned"
                    },
                    new Cosplay
                    {
                        CosplayId = Guid.NewGuid(),
                        Name = "Trafalgar D. Law",
                        Serie = "One Piece",
                        Budget= 120.0m,
                        StartDate = DateTime.Now,
                        DueDate = DateTime.Now,
                        Status = "Finished"
                    },
                    new Cosplay
                    {
                        CosplayId = Guid.NewGuid(),
                        Name = "Satoru Gojo",
                        Serie = "Jujutsu Kaisen",
                        Budget= 70.0m,
                        StartDate = DateTime.Now,
                        DueDate = DateTime.Now.AddDays(100),
                        Status = "In Progress"
                    },
                    new Cosplay
                    {
                        CosplayId = Guid.NewGuid(),
                        Name = "Misha Necron",
                        Serie = "Maō Gakuin no Futekigōsha ~Shijō Saikyō no Maō no Shiso, Tensei Shite Shison-tachi no Gakkō e Kayou~",
                        Budget= 120.0m,
                        StartDate = DateTime.Now,
                        DueDate = DateTime.Now.AddDays(170),
                        Status = "In Progress"
                    }
                };
                context.Cosplays.AddRange(cosplays);
                context.SaveChanges();

                var cosplayItems = new List<CosplayItem>
                {
                    new CosplayItem
                    {
                        CosplayItemId = Guid.NewGuid(),
                        Name = "Black Shirt",
                        Status = "To Buy",
                        Description = "It's a black shirt! What else?",
                        Price = 25.0m,
                        DueDate = DateTime.Now.AddMonths(1),
                        IsMade = false,
                        BuyLink = "https://www.kittyconnection.net/",
                        CosplayId = cosplays[0].CosplayId,
                        Cosplay = cosplays[0]
                    },
                    new CosplayItem
                    {
                        CosplayItemId = Guid.NewGuid(),
                        Name = "Black Trouser",
                        Status = "Completed",
                        Description = "It's a black trouser! What else?",
                        Price = 30.0m,
                        DueDate = DateTime.Now.AddMonths(2),
                        IsMade = false,
                        BuyLink = "https://www.kittyconnection.net/",
                        CosplayId = cosplays[0].CosplayId,
                        Cosplay = cosplays[0]
                    },
                    new CosplayItem
                    {
                        CosplayItemId = Guid.NewGuid(),
                        Name = "Black Gloves",
                        Status = "Ordered",
                        Description = "It's a black gloves! What else?",
                        Price = 15.0m,
                        DueDate = DateTime.Now.AddMonths(1),
                        IsMade = false,
                        BuyLink = "https://www.kittyconnection.net/",
                        CosplayId = cosplays[0].CosplayId,
                        Cosplay = cosplays[0]
                    },
                    new CosplayItem
                    {
                        CosplayItemId = Guid.NewGuid(),
                        Name = "Black Katana",
                        Status = "In Progress",
                        Description = "It's a black katana! Forged by the underdark dwarves",
                        Price = 150.0m,
                        DueDate = DateTime.Now.AddMonths(5),
                        IsMade = true,
                        Progress = 10,
                        WorkTime = 5,
                        CosplayId = cosplays[0].CosplayId,
                        Cosplay = cosplays[0]
                    },
                    new CosplayItem
                    {
                        CosplayItemId = Guid.NewGuid(),
                        Name = "Black Boots",
                        Status = "In Progress",
                        Description = "Their black boots! Made from demon-cow leather",
                        Price = 20.0m,
                        DueDate = DateTime.Now.AddMonths(1),
                        IsMade = true,
                        Progress = 50,
                        WorkTime = 3,
                        CosplayId = cosplays[0].CosplayId,
                        Cosplay = cosplays[0]
                    },
                    new CosplayItem
                    {
                        CosplayItemId = Guid.NewGuid(),
                        Name = "Black Cap",
                        Status = "Completed",
                        Description = "It's a black cap! To hide from nosy people or Corona",
                        Price = 15.0m,
                        DueDate = DateTime.Now.AddMonths(3),
                        IsMade = true,
                        Progress = 30,
                        WorkTime = 1,
                        CosplayId = cosplays[0].CosplayId,
                        Cosplay = cosplays[0]
                    }
                };
                context.CosplayItems.AddRange(cosplayItems);
                context.SaveChanges();
            }
        }

        public static void RecreateDatabase(ApplicationDbContext context)
        {
            context.Database.EnsureDeleted();
            context.Database.Migrate();
        }
    }
}
