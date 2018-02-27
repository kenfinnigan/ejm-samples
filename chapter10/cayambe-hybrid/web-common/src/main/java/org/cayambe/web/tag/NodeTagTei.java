/*
 * NodeTei.java
 *
 * Created on 17 ? 2001, 05:43
 */

package org.cayambe.web.tag;


import javax.servlet.jsp.tagext.TagData;
import javax.servlet.jsp.tagext.TagExtraInfo;
import javax.servlet.jsp.tagext.VariableInfo;


/**
 * Implementation of <code>TagExtraInfo</code> for the <b>node</b>
 * tag, identifying the scripting object(s) to be made visible.
 *
 * @author anatr
 * @version 0.1
 */

public class NodeTagTei extends TagExtraInfo {


    /**
     * Return information about the scripting variables to be created.
     */
    public VariableInfo[] getVariableInfo(TagData data) {

	return new VariableInfo[] {
	  new VariableInfo("node",
	                   "org.cayambe.web.tag.NodeDisplayDetails",
	                   true,
	                   VariableInfo.NESTED)
	};

    }


}