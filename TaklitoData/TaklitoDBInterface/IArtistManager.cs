using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;

namespace TaklitoDBInterface
{
    public interface IArtistManager
    {
        int AddArtist(string firstName, string lastName);
        void AddAlbumToArtist(Album album, string firstName, string lastName);
        Artist[] GetAllArtists();
        void RemoveArtist(int ArtistID);
    }
}
