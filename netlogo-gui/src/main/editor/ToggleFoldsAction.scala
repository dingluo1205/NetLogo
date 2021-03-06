// (C) Uri Wilensky. https://github.com/NetLogo/NetLogo

package org.nlogo.editor

import java.awt.event.ActionEvent
import javax.swing.AbstractAction

import org.nlogo.core.I18N

class ToggleFoldsAction(editorArea: AdvancedEditorArea)
  extends AbstractAction(I18N.gui.get("tabs.code.rightclick.collapse")) {

  override def actionPerformed(evt: ActionEvent): Unit = {
    val fm = editorArea.getFoldManager
    val foldsToToggle = for {
      i    <- 0 until fm.getFoldCount
      fold = fm.getFold(i)
      if (fold.getEndOffset > editorArea.getSelectionStart && fold.getStartOffset < editorArea.getSelectionEnd)
    } yield fold
    val allCollapsed = foldsToToggle.forall(_.isCollapsed)

    if (allCollapsed) foldsToToggle.foreach(_.setCollapsed(false))
    else              foldsToToggle.foreach(_.setCollapsed(true))
  }
}
