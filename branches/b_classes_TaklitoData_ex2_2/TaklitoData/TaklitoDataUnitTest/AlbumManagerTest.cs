﻿using System;
using System.Text;
using System.Collections.Generic;
using System.Linq;
using Microsoft.VisualStudio.TestTools.UnitTesting;
using TaklitoDBInterface;

namespace TaklitoDataUnitTest
{
    [TestClass]
    public class AlbumManagerTest
    {


               

        [TestMethod]
        public void GetAlbum()
        {
            TableMaster tm = new TableMaster();
            Album[] album = tm.GetAllAlbum();
            Assert.IsTrue(album[0].Name.Trim() == "Lies");
        }
    }
}
