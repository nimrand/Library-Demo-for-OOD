package business

import java.time.LocalDate

class LibraryMember(val id : LibraryMemberID, var name : PersonName, var joinedDate : LocalDate) {
  override def toString =
    "$id : $name"
}