using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;

namespace TaklitoDataDemo
{
    public class ArtistManager : IArtistManager
    {
        List<Artist> m_artists = new List<Artist>();

        #region IArtistManager Members

        public void RemoveArtist(int ArtistID)
        {
            throw new NotImplementedException();
        }

        public void AddArtist(string firstName, string lastName)
        {
            Artist artist = new Artist();
            artist.FirstName = firstName;
            artist.LastName = lastName;
            m_artists.Add(artist);
        }

        public void AddAlbumToArtist(Album album, string firstName, string lastName)
        {
            if (FindArtist(firstName, lastName) != null)
            {
                Artist art = FindArtist(firstName, lastName);
                art.Albumes.Add(album);
            }

        }

        public Artist[] GetAllArtists()
        {
            return m_artists.ToArray();
        }

        #endregion



        Artist FindArtist(string first, string last)
        {
            foreach (Artist art in m_artists)
            {
                if (art.FirstName == first && art.LastName == last)
                    return art;
            }
            return null;
        }
    }
}