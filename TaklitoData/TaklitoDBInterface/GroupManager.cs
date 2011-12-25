using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Data.Linq;
using System.Threading;

namespace TaklitoDBInterface
{
    public class GroupManager :IGroupManager
    {

        TaklitoContext m_context;
        DataContext m_db;


        public GroupManager()
        {
            m_context = TaklitoContext.GetIntance();
            m_db = m_context.TaklitoDataContext1;
        }

        private Table<Group> GetGroupTable()
        {
            return m_db.GetTable<Group>();
        }


        #region IGroupManager Members

        public void AddGroup(string name)
        {
            Group g_new = new Group();
            g_new.GroupID = Services.createIntGuid();
            g_new.Name = name;
            GetGroupTable().InsertOnSubmit(g_new);
            m_db.SubmitChanges();
        }


        public Group[] GetAllGroups()
        {
            if (!m_db.DatabaseExists()) return null;
            return GetGroupTable().ToArray();
        }

        public void RemoveGroup(int groupId)
        {
            if (!m_db.DatabaseExists()) throw new Exception("data base not exist");
            GetGroupTable().DeleteOnSubmit(GetGroupTable().Where(X => X.GroupID == groupId).Select(x => x).Single());
            m_db.SubmitChanges();
        }

        public void RemoveGroup(string GroupName)
        {
            int GroupId = GetGroupId(GroupName);
            RemoveGroup(GroupId);
        }

        public int GetGroupId(string GroupName)
        {
            if (!m_db.DatabaseExists()) return 0;
            return GetGroupTable().Where(X => X.Name == GroupName).FirstOrDefault().GroupID;
        }

        public void JoinArtistToGroup(Artist artist, ref Group group)
        {
            int groupId = (group == null) ? 0 : group.GroupID;
            JoinArtistToGroup(artist, group.GroupID);
            group = GetGroupTable().Where(X => X.GroupID == groupId).Single();
        }

        private void AddAritstIfNeeded(Artist artist)
        {
            Table<Artist> artists = m_db.GetTable<Artist>();
            if (artists.Where(a => a.ArtistID == artist.ArtistID).Count() == 0)
            {
                artists.InsertOnSubmit(artist);
                m_db.SubmitChanges();
            } 
        }

        private void AddAlbumIfNeeded(Album album)
        {
            Table<Album> albums = m_db.GetTable<Album>();
            if (albums.Where(a => a.AlbumId ==album.AlbumId).Count() == 0)
            {
                albums.InsertOnSubmit(album);
                m_db.SubmitChanges();
            } 
        }


        public void JoinArtistToGroup(Artist artist, int groupId)
        {
            AddAritstIfNeeded(artist);
            GetGroupTable().Where(X => X.GroupID == groupId).Single().Artists.Add(artist);
            m_db.SubmitChanges();
        }

        public void MoveArtistFromGroupToGroup(Artist artist, int groupFrom, int groupTo)
        {
            Group gfrom = GetGroupTable().Where(X => X.GroupID == groupFrom).Single();
            Group gto = GetGroupTable().Where(X => X.GroupID == groupTo).Single();
            gfrom.Artists.Remove(artist);
            gto.Artists.Add(artist);
            m_db.SubmitChanges();
            Thread.Sleep(5000);
        }

        public void RemoveArtistFromGroup(Artist artistId, int groupId)
        {
            Group g = GetGroupTable().Where(X => X.GroupID == groupId).Single();
            g.Artists.Remove(artistId);
            m_db.SubmitChanges();
        }

        public void AddAlbum(Album album, int groupId)
        {
            Group g = GetGroupTable().Where(X => X.GroupID == groupId).Single();
            g.Albums.Add(album);
            m_db.SubmitChanges();
        }

        public Group FindGroup(string name)
        {
            if (!m_db.DatabaseExists()) return null;
            return GetGroupTable().Where(X => X.Name == name).FirstOrDefault();
        }



        
        #endregion
    }
}
