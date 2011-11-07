using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;

namespace TaklitoDataDemo
{
    public class GroupManager:IGroupManager
    {




        #region IGroupManager Members

        public void AddGroup(string name)
        {
            throw new NotImplementedException();
        }

        public Group[] GetAllGroups()
        {
            throw new NotImplementedException();
        }

        public void RemoveGroup(int groupId)
        {
            throw new NotImplementedException();
        }

        public void RemoveGroup(string GroupName)
        {
            throw new NotImplementedException();
        }

        public int GetGroupId(string GroupName)
        {
            throw new NotImplementedException();
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

        public Group FindGroup(string name)
        {
            throw new NotImplementedException();
        }

        #endregion
    }
}
