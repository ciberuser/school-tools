using System;
using System.Text;
using System.Collections.Generic;
using System.Linq;
using Microsoft.VisualStudio.TestTools.UnitTesting;
using TaklitoDataDemo;

namespace TaklitoDataUnitTest
{
    /// <summary>
    /// Summary description for ArtistManagerTests
    /// </summary>
    [TestClass]
    public class ArtistManagerTests
    {
        const string FIRST_NAME = "firstname";
        const string LAST_NAME ="lastname";


        public ArtistManagerTests()
        {
            //
            // TODO: Add constructor logic here
            //
        }

        private TestContext testContextInstance;

        /// <summary>
        ///Gets or sets the test context which provides
        ///information about and functionality for the current test run.
        ///</summary>
        public TestContext TestContext
        {
            get
            {
                return testContextInstance;
            }
            set
            {
                testContextInstance = value;
            }
        }

        private IArtistManager GetArtistManger()
        {
            return new ArtistManager();
        }

        private Artist[] GetArtists()
        {
            return GetArtistManger().GetAllArtists();
        }

        Artist[] GenerateArtist(int cont)
        {
            Artist[] artArr = new Artist[cont];
            for (int i = 0; i < cont ;++i)
            {
                artArr[i].FirstName = FIRST_NAME + "_" + i;
                artArr[i].LastName = LAST_NAME + "_" + i;
             }
            return artArr;
        }

        #region Additional test attributes
        //
        // You can use the following additional attributes as you write your tests:
        //
        // Use ClassInitialize to run code before running the first test in the class
        // [ClassInitialize()]
        // public static void MyClassInitialize(TestContext testContext) { }
        //
        // Use ClassCleanup to run code after all tests in a class have run
        // [ClassCleanup()]
        // public static void MyClassCleanup() { }
        //
        // Use TestInitialize to run code before running each test 
        // [TestInitialize()]
        // public void MyTestInitialize() { }
        //
        // Use TestCleanup to run code after each test has run
        // [TestCleanup()]
        // public void MyTestCleanup() { }
        //
        #endregion

        [TestMethod]
        public void TestAddAlbumToArtist()
        {
            Album album = new Album();
            album.AlbumId =1;
            album.Name ="test";

            IArtistManager artManager = GetArtistManger();
            artManager.AddArtist("_" + FIRST_NAME + "_", "_" + LAST_NAME + "_");
            artManager.AddAlbumToArtist(album, "_" + FIRST_NAME + "_", "_" + LAST_NAME + "_");
            Assert.IsTrue(artManager.GetAllArtists()[0].Albumes[0].Name == "test");
        }

        [TestMethod]
        public void TestGetAllArtists()
        {
           Assert.IsTrue(GetArtists()!= null) ;
        }

        [TestMethod]
        public void TestAddArtist()
        {
            int toAdd = 2 ;
           
            IArtistManager manger = GetArtistManger();
            for (int i = 0; i < toAdd; ++i)
            {

                manger.AddArtist(FIRST_NAME + "_" + i, LAST_NAME + "_" + i);
            }

            Assert.IsTrue(manger.GetAllArtists()[0].FirstName == FIRST_NAME + "_" + 0);
            Assert.IsTrue(manger.GetAllArtists()[1].LastName == LAST_NAME + "_" + 1);

        }

    }
}
