package com.intellij.preservelayout;

import com.intellij.openapi.actionSystem.AnAction;
import com.intellij.openapi.actionSystem.AnActionEvent;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.vfs.VirtualFileManager;
import com.intellij.openapi.vfs.newvfs.BulkFileListener;
import com.intellij.openapi.vfs.newvfs.events.VFileEvent;
import groovy.ui.SystemOutputInterceptor;
import org.jetbrains.annotations.NotNull;

import java.util.List;

public class ShowProject extends AnAction {

    @Override
    public void actionPerformed(AnActionEvent e) {
        Project project = e.getProject();
        System.out.println("Hello. Project name is : " + project.getName());
        System.out.println(e.getProject().getProjectFile().getName());

        // maintain a set of changed filed

        project.getMessageBus().connect().subscribe(VirtualFileManager.VFS_CHANGES, new BulkFileListener() {
            @Override
            public void after(@NotNull List<? extends VFileEvent> events) {
                // handle the events
                System.out.println("chaged: " + events.toString());
            }
        });
    }
}
