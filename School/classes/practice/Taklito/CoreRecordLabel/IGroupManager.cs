﻿using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;

namespace TaklitoDataDemo
{
    public interface IGroupManager
    {
        void AddGroup(string name);
        List<Group> GetAllGroups();
        void RemoveGroup(int groupId);
        void JoinArtistToGroup(Artist artist, ref Group group);
        void JoinArtistToGroup(Artist artist, int groupId);
        void MoveArtistFromGroupToGroup(Artist artist, int groupFrom, int groupTo);
        void RemoveArtistFromGroup(Artist artistId, int groupId);
    }
}