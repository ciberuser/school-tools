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
            return TaklitoContext.GetIntance().TaklitoDataContext1.GetTable<Artist>();
        }

        public ArtistManager()
        {
                        
        }

        #region IArtistManager Members

        public void AddArtist(string firstName, string lastName)
        {
            Artist artist = new Artist();
            artist.FirstName = firstName;
            artist.LastName = lastName;
           
        }

        public void AddAlbumToArtist(Album album, string firstName, string lastName)
        {
            throw new NotImplementedException();
        }

        public Artist[] GetAllArtists()
        {
            throw new NotImplementedException();
        }

        #endregion
    }
}
