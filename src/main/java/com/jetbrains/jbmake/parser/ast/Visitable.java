package com.jetbrains.jbmake.parser.ast;

import com.jetbrains.jbmake.processing.SkippingNodesVisitor;

/**
 * @author daywalker
 * @since 14/06/15.
 */
public interface Visitable {
    void accept(SkippingNodesVisitor visitor);
}
