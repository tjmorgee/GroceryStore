package edu.ics372.gp1.business.collections;

import java.io.Serializable;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

import edu.ics372.gp1.business.entities.Member;

public class MemberList implements Iterable<Member>, Serializable {
	private static final long serialVersionUID = 1L;
	private List<Member> members = new LinkedList<Member>();
	private static MemberList memberList;

	private MemberList() {

	}

	public static MemberList getInstance() {
		if (memberList == null) {
			memberList = new MemberList();
		}
		return memberList;
	}
	
	/**
	 * Helper method for adding members to the list
	 * 
	 * @param member member to be added
	 * @return true
	 */
	public boolean addMember(Member member) {
		members.add(member);
		return true;
	}
	
	// Business Process 2
	public boolean removeMember(String id) {
		for (Iterator<Member> iterator = members.iterator(); iterator.hasNext();) {
			Member member = iterator.next();
			if (member.getId().equals(id)) {
				members.remove(member);
				return true;
			}
		}
		return false;
	}
	
	/**
	 * Checks whether a member with a given member name exists.
	 * 
	 * @param memberName the Name of the member
	 * @return member if found otherwise null
	 * 
	 */
	public Member search(String memberName) {
		for (Iterator<Member> iterator = members.iterator(); iterator.hasNext();) {
			Member member = iterator.next();
			if (member.getName().equalsIgnoreCase(memberName)) {
				return member;
			}
		}
		return null;
	}
	
	/**
	 * Checks whether a member with a given member id exists.
	 * 
	 * @param memberId the id of the member
	 * @return member if found otherwise null
	 * 
	 */
	public Member searchId(String memberId) {
		for (Iterator<Member> iterator = members.iterator(); iterator.hasNext();) {
			Member member = iterator.next();
			if (member.getId().equalsIgnoreCase(memberId)) {
				return member;
			}
		}
		return null;
	}

	@Override
	public Iterator<Member> iterator() {
		return members.iterator();
	}

}
