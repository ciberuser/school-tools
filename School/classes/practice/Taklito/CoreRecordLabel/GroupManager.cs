using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;

namespace TaklitoDataDemo
{
    public class GroupManager:IGroupManager
    {

        List<Group> m_groups;

        #region IGroupManager Members

        public GroupManager()
        {
            m_groups = new List<Group>();
        }

        public void AddGroup(string name)
        {
            Group newGroup = new Group(name);
            m_groups.Add(newGroup);
        }

        public List<Group> GetAllGroups()
        {
            return m_groups;
        }

        public void JoinArtistToGroup(Artist artist, ref Group group)
        {
            if (group!= null) group.Artists.Add(artist);
        }

        public void JoinArtistToGroup(Artist artist, int groupId)
        {
            try
            {
                int index = FindGroup(groupId);
                m_groups[index].Artists.Add(artist);
            }
            catch (System.Exception ex)
            {
            	ErrorFind(ex);
            }
              
            
        }

        public void MoveArtistFromGroupToGroup(Artist artist, int groupFrom, int groupTo)
        {
            int to, from;
            try
            {
                from = FindGroup(groupFrom);
                to = FindGroup(groupFrom);
                m_groups[to].Artists.Add(artist);
                m_groups[from].Artists.Remove(artist);
            }

            catch (System.Exception ex)
            {
                ErrorFind(ex);
            }
        }

        public void LeaveArtistFromGroup(Artist artistId, int groupId)
        {
            try
            {
                m_groups[FindGroup(groupId)].Artists.Remove(artistId);
            }
            catch (System.Exception ex)
            {
                ErrorFind(ex);
            }
        }

       
        int FindGroup(int id)
        {
            int i = 0;
            foreach (Group g in m_groups)
            {
                i++;
                if (g.GroupID == id)
                {
                    return i;
                }
            }
            return -1;
        }

        void ErrorFind(Exception ex)
        {
            throw new Exception(string.Format("can't find group error{0}", ex.Message));
        }
        #endregion
    }
}
