﻿using System;
using System.Collections.Generic;
using System.Linq;
using System.Threading.Tasks;
using CosNet.API.Services;
using Microsoft.Extensions.DependencyInjection;

namespace CosNet.API.StartupSections.Configuration
{
    public static class ServiceConfiguration
    {
        public static IServiceCollection AddCosNetServices(this IServiceCollection services)
        {
            services.AddHttpContextAccessor();

            services.AddTransient<IUserService, UserService>();
            services.AddTransient<ICosplayService, CosplayService>();
            services.AddTransient<ICosplayItemService, CosplayItemService>();
            services.AddTransient<ICosplayItemMaterialService, CosplayItemMaterialService>();

            return services;
        }
    }
}
