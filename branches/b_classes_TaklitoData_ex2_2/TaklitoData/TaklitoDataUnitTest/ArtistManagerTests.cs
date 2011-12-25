using System;
using System.Text;
using System.Collections.Generic;
using System.Linq;
using Microsoft.VisualStudio.TestTools.UnitTesting;
//////////////////////////////////////////////////////////////////////////
//if you using linq please unmark this flow line.
using System.Data.Linq;
//////////////////////////////////////////////////////////////////////////
using TaklitoDBInterface;



namespace TaklitoDataUnitTest
{
    /// <summary>
    /// Summary description for ArtistManagerTests
    /// </summary>
    [TestClass]
    public class ArtistManagerTests
    {
        const string FIRST_NAME = Services.FIRST_NAME;
        const string LAST_NAME = Services.LAST_NAME;

        IArtistManager m_am;

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
            if (m_am==null) m_am =  new ArtistManager();
            return m_am;
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
                artArr[i].FirstName = FIRST_NAME +  i;
                artArr[i].LastName = LAST_NAME +  i;
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
        [TestCleanup()]
        public void MyTestCleanup() 
        {
            Services.CleanDB();
        }
        
        #endregion

        [TestMethod]
        public void TestAddAlbumToArtist()
        {
            Album album = new Album();
            album.AlbumId = Services.createIntGuid();
            album.Name =Services.ALBUM;
            
            IArtistManager artManager = GetArtistManger();
            int size = artManager.GetAllArtists().Length;
            int artistId = artManager.AddArtist(FIRST_NAME , LAST_NAME );
            artManager.AddAlbumToArtist(album, FIRST_NAME ,LAST_NAME);
            Assert.IsTrue(artManager.GetAllArtists().Where(X => X.ArtistID ==artistId).Single().Albums[0].Name == Services.ALBUM);
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
            int[] IdArr = new int[toAdd] ;
            IArtistManager manger = GetArtistManger();
            int size = manger.GetAllArtists().Length;
            for (int i = 0; i < toAdd; ++i)
            {
                IdArr[i] = manger.AddArtist(FIRST_NAME + i, LAST_NAME + i);
            }

            Assert.IsTrue(manger.GetAllArtists().Where(X => X.ArtistID == IdArr[0]).Single().FirstName == FIRST_NAME + "0");
            Assert.IsTrue(manger.GetAllArtists().Where(X => X.ArtistID == IdArr[1]).Single().LastName == LAST_NAME + "1");
        }

    }
}
