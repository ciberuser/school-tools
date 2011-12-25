using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;

//////////////////////////////////////////////////////////////////////////
//add your name space
using TaklitoDBInterface;
//////////////////////////////////////////////////////////////////////////


namespace TaklitoDataUnitTest
{
    class Services
    {
        public Services()
        {

        }

        public const string GROUP = "group";
        public const string ARTIST = "artist";
        public const string ALBUM = "album";
        public const string FIRST_NAME = "firstname";
        public const string LAST_NAME = "lastname";

        public static int createIntGuid()
        {
            byte[] seed = Guid.NewGuid().ToByteArray();
            for (int i = 0; i < 3; i++)
            {
                seed[i] ^= seed[i + 4];
                seed[i] ^= seed[i + 8];
                seed[i] ^= seed[i + 12];
            }

            return BitConverter.ToInt32(seed, 0);
        }

        public static void CleanDB()
        {
            IArtistManager im = new ArtistManager();
            IAlbumManager iAlbmM = new AlbumMaster();
            IGroupManager gm = new GroupManager();

            foreach (Album al in iAlbmM.GetAllAlbum())
            {
                if (al.Name.Contains(ALBUM))
                    iAlbmM.RemoveAlbum(al.AlbumId);
            }

            foreach (Artist a in im.GetAllArtists())
            {
                if (a.FirstName.Contains(FIRST_NAME) && a.LastName.Contains(LAST_NAME))
                {
                    
                   im.RemoveArtist(a.ArtistID);
                  
                }
            }
                      
            foreach (Group g in gm.GetAllGroups())
            {
                if (g.Name.Contains(GROUP))
                    gm.RemoveGroup(g.GroupID);
            }
        }


    }
}
