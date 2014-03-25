package com.marcneveling.gui;

import com.jgoodies.binding.PresentationModel;
import com.marcneveling.main.PageModel;

public class GuiPresentationModel extends PresentationModel<PageModel> {
	private PageModel page;
	
	public GuiPresentationModel(PageModel page){
		super(page);
		this.page = page;
	}
}
