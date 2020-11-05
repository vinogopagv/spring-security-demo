package com.example.securitydemo.config;

import java.util.Set;

import com.google.common.collect.Sets;

public enum ApplicationUserRole {

	STUDENT(Sets.newHashSet()),
	ADMIN(Sets.newHashSet(ApplicationuserPermission.STUDENT_READ,ApplicationuserPermission.STUDENT_WRITE,
			ApplicationuserPermission.COURSE_WRITE,ApplicationuserPermission.COURSE_READ)),
	ADMIN_TRAINEE(Sets.newHashSet(ApplicationuserPermission.STUDENT_READ,ApplicationuserPermission.COURSE_READ));

	private final Set<ApplicationuserPermission> permissions;


	private ApplicationUserRole(Set<ApplicationuserPermission> permissions) {
		this.permissions = permissions;
	}


	public Set<ApplicationuserPermission> getPermissions() {
		return permissions;
	}



}
