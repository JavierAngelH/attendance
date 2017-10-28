/** 
 * Created by JavierAngelH
 */

package com.app.attendance.utils;

import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStream;

import com.vaadin.server.Page;
import com.vaadin.ui.Notification;
import com.vaadin.ui.Upload.Receiver;
import com.vaadin.ui.Upload.SucceededEvent;
import com.vaadin.ui.Upload.SucceededListener;

/**
 * AdvertUploader - 
 *
 */
public class AdvertUploader implements Receiver, SucceededListener {
	private static final long serialVersionUID = 1L;
	public File file;

	@Override
	public OutputStream receiveUpload(String filename, String mimeType) {
		// Create upload stream
		FileOutputStream fos = null;
		try {
			// Change uploaded files path
			this.file = new File(Utilities.documentsPath + filename);
			fos = new FileOutputStream(this.file);
		} catch (final java.io.FileNotFoundException e) {
			new Notification("Could not open file<br/>", e.getMessage(), Notification.Type.ERROR_MESSAGE)
					.show(Page.getCurrent());
			return null;
		}
		return fos; // Return the output stream to write to
	}

	@Override
	public void uploadSucceeded(SucceededEvent event) {
		Notification.show("Image uploaded");
	}
}