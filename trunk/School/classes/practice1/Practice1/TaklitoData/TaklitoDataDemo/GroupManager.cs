using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;

namespace TaklitoDataDemo
{
    public class GroupManager:IGroupManager
    {

        List<Group> m_groups;
        int m_indexCont = 0;
        #region IGroupManager Members

        public GroupManager()
        {
            m_groups = new List<Group>();
        }

        public void AddGroup(string name)
        {
             
            Group newGroup = new Group(name);
            newGroup.GroupID = m_indexCont;
            m_groups.Add(newGroup);
            m_indexCont++;
        }

        public Group[] GetAllGroups()
        {
            return m_groups.ToArray();
        }

        public void AddAlbum(Album album, int groupId)
        {
            m_groups[FindGroupIndex(groupId)].Albumes.Add(album);
           
        }

        public void JoinArtistToGroup(Artist artist, ref Group group)
        {
            
            if (group != null)
            {
                artist.Group = group;
                group.Artists.Add(artist);
            }
        }

        public void JoinArtistToGroup(Artist artist, int groupId)
        {
            try
            {
                int index = FindGroupIndex(groupId);
                Group g = m_groups[index];
                JoinArtistToGroup(artist, ref g );
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
                from = FindGroupIndex(groupFrom);
                to = FindGroupIndex(groupTo);
                m_groups[to].Artists.Add(artist);
                m_groups[from].Artists.Remove(artist);
            }

            catch (System.Exception ex)
            {
                ErrorFind(ex);
            }
        }

        public void RemoveArtistFromGroup(Artist artistId, int groupId)
        {
            try
            {
                m_groups[FindGroupIndex(groupId)].Artists.Remove(artistId);
            }
            catch (System.Exception ex)
            {
                ErrorFind(ex);
            }
        }

        Group GetGroup(int id)
        {
            foreach (Group g in m_groups)
            {
                if (g.GroupID == id) return g;

            }
            return null;
        }
        
        int FindGroupIndex(int id)
        {
            int i = 0;
            foreach (Group g in m_groups)
            {
                if (g.GroupID == id)
                {
                    return i;
                }
                i++;
            }
            return -1;
        }

        void ErrorFind(Exception ex)
        {
            throw new Exception(string.Format("can't find group error{0}", ex.Message));
        }

       
        public void RemoveGroup(int groupId)
        {
            m_groups.Remove(GetGroup(groupId));
        }

        public Group FindGroup(string name)
        {
            foreach (Group g in m_groups)
            {
                if (g.Name == name) return g;
            }
            return null;
        }
       
        public void RemoveGroup(string GroupName)
        {
            m_groups.Remove(FindGroup(GroupName));  
        }

        public int  GetGroupId(string GroupName)
        {
            return FindGroup(GroupName).GroupID;
        }

        #endregion
    }
}
