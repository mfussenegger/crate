/*
 * Licensed to CRATE Technology GmbH ("Crate") under one or more contributor
 * license agreements.  See the NOTICE file distributed with this work for
 * additional information regarding copyright ownership.  Crate licenses
 * this file to you under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.  You may
 * obtain a copy of the License at
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.  See the
 * License for the specific language governing permissions and limitations
 * under the License.
 *
 * However, if you have executed another commercial license agreement
 * with Crate these terms will supersede the license and you may use the
 * software solely pursuant to the terms of the relevant commercial agreement.
 */

package io.crate.sql.tree;

import com.google.common.base.MoreObjects;
import com.google.common.base.Objects;
import com.google.common.base.Preconditions;

public class Intersect<T> extends SetOperation<T> {

    private final Relation<T> left;
    private final Relation<T> right;

    public Intersect(Relation<T> left, Relation<T> right) {
        this.left = Preconditions.checkNotNull(left, "relation must not be null");
        this.right = Preconditions.checkNotNull(right, "relation must not be null");
    }

    public Relation<T> getLeft() {
        return left;
    }

    public Relation<T> getRight() {
        return right;
    }

    @Override
    public <R, C> R accept(AstVisitor<R, C> visitor, C context) {
        return visitor.visitIntersect(this, context);
    }

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this)
            .add("left", left)
            .add("right", right)
            .toString();
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if ((obj == null) || (getClass() != obj.getClass())) {
            return false;
        }
        Intersect o = (Intersect) obj;
        return Objects.equal(left, o.left) && Objects.equal(right, o.right);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(left, right);
    }
}
