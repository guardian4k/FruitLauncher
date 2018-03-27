package net.teamfruit.skcraft.launcher.skins;

import java.io.File;

import com.skcraft.launcher.Launcher;
import com.skcraft.launcher.persistence.Persistence;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import net.teamfruit.skcraft.launcher.model.skins.SkinInfo;

@RequiredArgsConstructor
public class LocalSkin {
	private final Launcher launcher;

	@Getter
	private final String name;

	public File getDir() {
		return new File(launcher.getSkinDir(), name);
	}

	public File getFile() {
		return new File(getDir(), "skin.json");
	}

	public File getResourceDir() {
		return new File(getDir(), "resources");
	}

	public boolean exists() {
		return getFile().isFile();
	}

	@Getter(lazy = true, value = AccessLevel.PRIVATE)
	private final SkinInfo backingSkin = Persistence.load(getFile(), SkinInfo.class, true);

	@Setter
	private SkinInfo skin;

	public Skin getSkin() {
		if (skin==null)
			skin = getBackingSkin();
		return new SkinData(launcher, skin, getResourceDir());
	}
}
