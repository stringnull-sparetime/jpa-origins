package org.stringnull.core.data;

import java.util.Optional;

public interface JPAOriginsCrudRepository<T,ID> {
   Optional<T> findById(ID id);
   Optional<T> save();
}
