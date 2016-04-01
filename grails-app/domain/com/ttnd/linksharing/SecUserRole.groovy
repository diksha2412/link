package com.ttnd.linksharing

import grails.gorm.DetachedCriteria
import groovy.transform.ToString

import org.apache.commons.lang.builder.HashCodeBuilder

@ToString(cache=true, includeNames=true, includePackage=false)
class SecUserRole implements Serializable {

	private static final long serialVersionUID = 1

	SecUser secUser
	Role role

	SecUserRole(SecUser u, Role r) {
		this()
		secUser = u
		role = r
	}

	@Override
	boolean equals(other) {
		if (!(other instanceof SecUserRole)) {
			return false
		}

		other.secUser?.id == SecUser?.id && other.role?.id == role?.id
	}

	@Override
	int hashCode() {
		def builder = new HashCodeBuilder()
		if (SecUser) builder.append(SecUser.id)
		if (role) builder.append(role.id)
		builder.toHashCode()
	}

	static SecUserRole get(long secUserId, long roleId) {
		criteriaFor(secUserId, roleId).get()
	}

	static boolean exists(long secUserId, long roleId) {
		criteriaFor(secUserId, roleId).count()
	}

	private static DetachedCriteria criteriaFor(long secUserId, long roleId) {
		SecUserRole.where {
			SecUser == SecUser.load(secUserId) &&
			role == Role.load(roleId)
		}
	}

	static SecUserRole create(SecUser secUser, Role role, boolean flush = false) {
		def instance = new SecUserRole(secUser: secUser, role: role)
		instance.save(flush: flush, insert: true)
		instance
	}

	static boolean remove(SecUser u, Role r, boolean flush = false) {
		if (u == null || r == null) return false

		int rowCount = SecUserRole.where { SecUser == u && role == r }.deleteAll()

		if (flush) { SecUserRole.withSession { it.flush() } }

		rowCount
	}

	static void removeAll(SecUser u, boolean flush = false) {
		if (u == null) return

		SecUserRole.where { SecUser == u }.deleteAll()

		if (flush) { SecUserRole.withSession { it.flush() } }
	}

	static void removeAll(Role r, boolean flush = false) {
		if (r == null) return

		SecUserRole.where { role == r }.deleteAll()

		if (flush) { SecUserRole.withSession { it.flush() } }
	}

	static constraints = {
		role validator: { Role r, SecUserRole ur ->
			if (ur.secUser == null || ur.secUser.id == null) return
			boolean existing = false
			SecUserRole.withNewSession {
				existing = SecUserRole.exists(ur.secUser.id, r.id)
			}
			if (existing) {
				return 'userRole.exists'
			}
		}
	}

	static mapping = {
		id composite: ['SecUser', 'role']
		version false
	}
}
