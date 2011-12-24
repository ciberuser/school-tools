using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Data.Linq;

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

        
        #region IGroupManager Members

        public void AddGroup(string name)
        {
            Group g_new = new Group();
            g_new.GroupID = Services.createIntGuid();
            g_new.Name = name;
            m_db.GetTable<Group>().InsertOnSubmit(g_new);
            m_db.SubmitChanges();
        }


        public Group[] GetAllGroups()
        {
            if (!m_db.DatabaseExists()) return null;
            return m_db.GetTable<Group>().ToArray();
        }

        public void RemoveGroup(int groupId)
        {
            if (!m_db.DatabaseExists()) throw new Exception("data base not exist");
            m_db.GetTable<Group>().DeleteOnSubmit(m_db.GetTable<Group>().Where(X => X.GroupID == groupId).Select(x => x).Single());
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
            return m_db.GetTable<Group>().Where(X => X.Name == GroupName).FirstOrDefault().GroupID;
        }

        public void JoinArtistToGroup(Artist artist, ref Group group)
        {
            throw new NotImplementedException();
        }

        public void JoinArtistToGroup(Artist artist, int groupId)
        {
            throw new NotImplementedException();
        }

        public void MoveArtistFromGroupToGroup(Artist artist, int groupFrom, int groupTo)
        {
            throw new NotImplementedException();
        }

        public void RemoveArtistFromGroup(Artist artistId, int groupId)
        {
            throw new NotImplementedException();
        }

        public void AddAlbum(Album album, int groupId)
        {
            throw new NotImplementedException();
        }

        public Group FindGroup(string name)
        {
            if (!m_db.DatabaseExists()) return null;
            return m_db.GetTable<Group>().Where(X => X.Name == name).FirstOrDefault();
        }



        
        #endregion
    }
}
