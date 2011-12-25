using System;
using System.Text;
using System.Collections.Generic;
using System.Linq;
using Microsoft.VisualStudio.TestTools.UnitTesting;
//using TaklitoDataDemo;
using TaklitoDBInterface;
using System.Data.Linq;

namespace TaklitoDataUnitTest
{

    
    /// <summary>
    /// Summary description for UnitTest1
    /// </summary>
    [TestClass]
    public class GroupManagerTest
    {
        const string GROUP = Services.GROUP;
        const string ARTIST = Services.ARTIST;
        const string ALBUM = Services.ALBUM;

        IGroupManager m_gropManager;
        private TestContext testContextInstance;

        public GroupManagerTest()
        {
            //
            // TODO: Add constructor logic here
            //
        }

        
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
            // CleanDBGroup();
         }
        //
        #endregion

        IGroupManager GetGroupManager()
        {
            try
            {
                if (m_gropManager == null) m_gropManager = new GroupManager();
                return m_gropManager;
                    
            }
            catch (System.Exception)
            {
                return null;
            }
        }

        Artist[] CreateArtists(int  size)
        {
            List<Artist> ar = new List<Artist>();
            for(int i=0 ; i<size ;i++)
            {
                Artist a = new Artist();
                a.ArtistID = i+Services.createIntGuid();
                a.FirstName = Services.FIRST_NAME + i.ToString();
                a.LastName = Services.LAST_NAME+  i.ToString();
                a.Position = ARTIST + "p" + i.ToString();
                ar.Add(a);
            }
            return ar.ToArray();
        }


        IGroupManager AddGroupsGroup(int size)
        {
            IGroupManager gm= GetGroupManager();
            return  AddGroupsGroup(size, gm);
           
        }

        IGroupManager AddGroupsGroup(int cont, IGroupManager gm)
        {
            for (int i = 0; i < cont; i++)
            {
                gm.AddGroup(GROUP + i.ToString());
            }
            return gm;
        }


        Group findSafeGroup(string name,IGroupManager gm)
        {
            Group[] allGroups = gm.GetAllGroups();
            foreach (Group g in allGroups)
            {
                if (g.Name == name) return g;
            }
            return null;
        }

        int GetSafeGroupid(string name, IGroupManager gm)
        {
            return findSafeGroup(name, gm).GroupID;
        }

        [TestMethod]
        public void TestAddGroup()
        {
            IGroupManager gm = GetGroupManager();
            int size = gm.GetAllGroups().Length;
            AddGroupsGroup(1, gm); 
             Assert.IsTrue(gm.GetAllGroups().Length == (size + 1));
        }

        [TestMethod]
        public void TestGetAllGroup()
        {
            Assert.IsTrue(GetGroupManager().GetAllGroups().GetType().IsArray);
        }

        [TestMethod]
        public void TestFindGroup()
        {
            IGroupManager gm = AddGroupsGroup(3);
            Group[] allGroups =  gm.GetAllGroups();
            
            foreach (Group g in allGroups)
            {
                string name = g.Name;
                Assert.IsTrue(gm.FindGroup(name) == g);
            }
        }

        [TestMethod]
        public void TestCreateClass()
        {
            Assert.IsTrue(GetGroupManager() != null);
        }

        [TestMethod]
        public void TestGroupExist()
        {
            string band = "Guns N' Roses";
            IGroupManager gm = GetGroupManager();
            gm.AddGroup(band);
            Assert.IsTrue(gm.FindGroup(band).Name == band);
            Group g = gm.FindGroup(band);
            gm.RemoveGroup(g.GroupID);
        }

        [TestMethod]
        public void TestRemoveGroup1()
        {
            IGroupManager gm = AddGroupsGroup(2);
            gm.RemoveGroup(GROUP +"1");
            Assert.IsTrue(findSafeGroup(GROUP + "1",gm) == null);
         }

        [TestMethod]
        public void TestValidGroupId()
        {
            IGroupManager gm = AddGroupsGroup(1);
            int groupid = gm.GetGroupId(GROUP + "0");
            Group group = findSafeGroup(GROUP + "0", gm);
            Assert.IsTrue(group.GroupID == groupid);
             
        }

        [TestMethod]
        public void TestRemoveGroup2()
        {
            IGroupManager gm = AddGroupsGroup(2);
            Group group = findSafeGroup(GROUP + "0", gm);
            gm.RemoveGroup(group.GroupID);
            Assert.IsTrue(findSafeGroup(GROUP + "0", gm) == null);
        }

        [TestMethod]
        public void TestJoinArtist()
        {
            IGroupManager gm= AddGroupsGroup(1);
            Artist[] artists = CreateArtists(1);
            int groupID = GetSafeGroupid(GROUP + "0", gm);
            gm.JoinArtistToGroup(artists[0], groupID);
            Group g = findSafeGroup(GROUP+"0",gm);
            Assert.IsTrue(g.Artists[0] == artists[0]);
        }

        [TestMethod]
        public void TestJoinArtist2()
        {
            IGroupManager gm = AddGroupsGroup(1);
            Artist[] artists = CreateArtists(1);
            Group g = findSafeGroup(GROUP + "0", gm);
            gm.JoinArtistToGroup(artists[0], ref g);
            Assert.IsTrue(g.Artists[0] == artists[0]);
        }

        [TestMethod]
        public void TestMoveArtist()
        {
            string grp1 = GROUP + "0" ;
            string grp2 = GROUP + "1";
            IGroupManager gm = AddGroupsGroup(2);
            Artist[] artists = CreateArtists(1);
            int gid1 = GetSafeGroupid(grp1, gm);
            int gid2 = GetSafeGroupid(grp2, gm);
            gm.JoinArtistToGroup(artists[0],gid1);
            gm.MoveArtistFromGroupToGroup(artists[0], gid1, gid2);

            Group g1 = findSafeGroup(grp1,gm);
            Group g2 = findSafeGroup(grp2,gm);
            Assert.IsTrue(g1.Artists.Count == 0 && g2.Artists[0] == artists[0]);
        }

        [TestMethod]
        public void TestRemoveArtist()
        {
            string grp1 = GROUP + "0" ;
            IGroupManager gm = AddGroupsGroup(1);
            Artist[] artists = CreateArtists(1);
            int gid1 = GetSafeGroupid(grp1, gm);
            gm.JoinArtistToGroup(artists[0], gid1);
            
            gm.RemoveArtistFromGroup(artists[0], gid1);
            foreach (Group g in gm.GetAllGroups())
            {
                if (g.Name.Contains(GROUP))
                {
                    Assert.IsTrue(g.Artists.Count == 0);
                    break;
                }
 
            }
           
        }

        [TestMethod]
        public void TestAddAlbum()
        {
            string albumName =ALBUM;
            string grp1 =GROUP + "0" ;   
            Album album = new Album();
            album.AlbumId = Services.createIntGuid();
            album.Name = albumName;
            IGroupManager gm = AddGroupsGroup(1);
            int gid1 = GetSafeGroupid(grp1, gm);
            gm.AddAlbum(album, gid1);
          //  Assert.IsTrue(gm.GetAllGroups()[0].Albums[0].Name == albumName);
        }

        

    }
}
