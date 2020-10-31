﻿using System;
using System.Collections.Generic;
using System.Linq;
using System.Threading.Tasks;

namespace CosNet.API.Entities
{
    public class CosplayMadeItem
    {
        public Guid CosplayItemId { get; set; }

        public DateTime StartDate { get; set; }
        public DateTime EndDate { get; set; }
        public int Progress { get; set; }
        public TimeSpan WorkTime { get; set; }
    }
}
