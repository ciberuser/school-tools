using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Data.Linq;

namespace TaklitoDBInterface
{
    public class ArtistManager: IArtistManager
    {

        private Table<Artist> GetArtistsTable()
        {
            if (!TaklitoContext.GetIntance().TaklitoDataContext1.DatabaseExists()) return null;
            return TaklitoContext.GetIntance().TaklitoDataContext1.GetTable<Artist>();
        }

        public ArtistManager()
        {
                        
        }

        #region IArtistManager Members

        public int AddArtist(string firstName, string lastName)
        {
            Artist artist = new Artist();
            artist.FirstName = firstName;
            artist.LastName = lastName;
            artist.ArtistID = Services.createIntGuid();
            GetArtistsTable().InsertOnSubmit(artist);
            TaklitoContext.GetIntance().TaklitoDataContext1.SubmitChanges();
            return artist.ArtistID;
        }

        public void AddAlbumToArtist(Album album, string firstName, string lastName)
        {
            Artist a = GetArtistsTable().Where(X => X.FirstName == firstName && X.LastName == lastName).FirstOrDefault();
            a.Albums.Add(album);
            TaklitoContext.GetIntance().TaklitoDataContext1.SubmitChanges();
        }

        public Artist[] GetAllArtists()
        {
            return GetArtistsTable().ToArray();
        }

        public void RemoveArtist(int ArtistID)
        {
            GetArtistsTable().DeleteOnSubmit(GetArtistsTable().Where(X => X.ArtistID == ArtistID).Select(x => x).Single());
            TaklitoContext.GetIntance().TaklitoDataContext1.SubmitChanges();
        }

        #endregion
    }
}
