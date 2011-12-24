using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;

namespace TaklitoDBInterface
{
    public interface IGroupManager
    {
        void AddGroup(string name);
        Group[] GetAllGroups();
        void RemoveGroup(int groupId);
        void RemoveGroup(string GroupName);
        int GetGroupId(string GroupName);
        void JoinArtistToGroup(Artist artist, ref Group group);
        void JoinArtistToGroup(Artist artist, int groupId);
        void MoveArtistFromGroupToGroup(Artist artist, int groupFrom, int groupTo);
        void RemoveArtistFromGroup(Artist artistId, int groupId);
        void AddAlbum(Album album, int groupId);
        Group FindGroup(string name);

    }
}
