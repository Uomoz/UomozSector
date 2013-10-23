package data.scripts.weapons;
import com.fs.starfarer.api.Global;
import com.fs.starfarer.api.combat.CombatEngineAPI;
import com.fs.starfarer.api.combat.CombatEntityAPI;
import com.fs.starfarer.api.combat.DamagingProjectileAPI;
import com.fs.starfarer.api.combat.OnHitEffectPlugin;
import com.fs.starfarer.api.combat.ShipAPI;
import java.awt.Color;
import org.lwjgl.util.vector.Vector2f;

public class AMLanceOnHitEffect implements OnHitEffectPlugin {
    
        public Vector2f particleVelocity1;
        public Vector2f particleVelocity2;
        public float damageAmount = 125f; //set this to whatever the damage should be
        public float empAmount = 0f; //set this to whatever the EMP damage should be
        public Color particleColor = new Color(130,205,217,255);
        public float particleSize = 3f;
        public float particleBrightness = 0.98f;
        public float particleDuration = 7.0f;
		public float explosionSize = 379f;
		public float explosionSize2 = 161f;
		public float explosionDuration = 1.37f;
		public float explosionDuration2 = 4.0f;
        public float pitch = 1f; //sound pitch. Default seems to be 1
        public float volume = 1f; //volume, scale from 0-1
        public String soundName = "brdy_amlance_impact"; //assign the sound we want to play
        public boolean dealsSoftFlux = false;
        
        @Override
	public void onHit(DamagingProjectileAPI projectile, CombatEntityAPI target, Vector2f point, boolean shieldHit, CombatEngineAPI engine) {
            if ((float) Math.random() > 0.0f && target instanceof ShipAPI) {
                particleVelocity1 = projectile.getVelocity();
                particleVelocity2 = projectile.getVelocity();
                particleVelocity1.scale(0.09f);
                particleVelocity2.scale(0.14f);

                engine.addHitParticle(point, particleVelocity1, particleSize, particleBrightness, particleDuration, particleColor);
                engine.addHitParticle(point, particleVelocity2, particleSize, particleBrightness, particleDuration, particleColor);	
                engine.addHitParticle(point, particleVelocity1, particleSize, particleBrightness, particleDuration, particleColor);
                engine.addHitParticle(point, particleVelocity2, particleSize, particleBrightness, particleDuration, particleColor);
                engine.addHitParticle(point, particleVelocity1, particleSize, particleBrightness, particleDuration, particleColor);
                engine.addHitParticle(point, particleVelocity2, particleSize, particleBrightness, particleDuration, particleColor);	
                engine.addHitParticle(point, particleVelocity1, particleSize, particleBrightness, particleDuration, particleColor);
                engine.addHitParticle(point, particleVelocity2, particleSize, particleBrightness, particleDuration, particleColor);
                engine.addHitParticle(point, particleVelocity2, particleSize, particleBrightness, particleDuration, particleColor);	
                engine.addHitParticle(point, particleVelocity1, particleSize, particleBrightness, particleDuration, particleColor);
                engine.addHitParticle(point, particleVelocity2, particleSize, particleBrightness, particleDuration, particleColor);
                engine.addHitParticle(point, particleVelocity1, particleSize, particleBrightness, particleDuration, particleColor);
                engine.addHitParticle(point, particleVelocity2, particleSize, particleBrightness, particleDuration, particleColor);
                engine.addHitParticle(point, particleVelocity1, particleSize, particleBrightness, particleDuration, particleColor);
                engine.addHitParticle(point, particleVelocity2, particleSize, particleBrightness, particleDuration, particleColor);	
                engine.addHitParticle(point, particleVelocity1, particleSize, particleBrightness, particleDuration, particleColor);
                engine.addHitParticle(point, particleVelocity2, particleSize, particleBrightness, particleDuration, particleColor);
                engine.addHitParticle(point, particleVelocity1, particleSize, particleBrightness, particleDuration, particleColor);
                engine.addHitParticle(point, particleVelocity2, particleSize, particleBrightness, particleDuration, particleColor);	
                engine.addHitParticle(point, particleVelocity1, particleSize, particleBrightness, particleDuration, particleColor);
                engine.addHitParticle(point, particleVelocity2, particleSize, particleBrightness, particleDuration, particleColor);
                engine.addHitParticle(point, particleVelocity2, particleSize, particleBrightness, particleDuration, particleColor);	
                engine.addHitParticle(point, particleVelocity1, particleSize, particleBrightness, particleDuration, particleColor);
                engine.addHitParticle(point, particleVelocity2, particleSize, particleBrightness, particleDuration, particleColor);
                engine.addHitParticle(point, particleVelocity1, particleSize, particleBrightness, particleDuration, particleColor);
                engine.addHitParticle(point, particleVelocity2, particleSize, particleBrightness, particleDuration, particleColor);
                engine.addHitParticle(point, particleVelocity1, particleSize, particleBrightness, particleDuration, particleColor);
                engine.addHitParticle(point, particleVelocity2, particleSize, particleBrightness, particleDuration, particleColor);	
                engine.addHitParticle(point, particleVelocity1, particleSize, particleBrightness, particleDuration, particleColor);
                engine.addHitParticle(point, particleVelocity2, particleSize, particleBrightness, particleDuration, particleColor);
                engine.addHitParticle(point, particleVelocity1, particleSize, particleBrightness, particleDuration, particleColor);
                engine.addHitParticle(point, particleVelocity2, particleSize, particleBrightness, particleDuration, particleColor);	
                engine.addHitParticle(point, particleVelocity1, particleSize, particleBrightness, particleDuration, particleColor);
                engine.addHitParticle(point, particleVelocity2, particleSize, particleBrightness, particleDuration, particleColor);
                engine.addHitParticle(point, particleVelocity2, particleSize, particleBrightness, particleDuration, particleColor);	
                engine.addHitParticle(point, particleVelocity1, particleSize, particleBrightness, particleDuration, particleColor);
                engine.addHitParticle(point, particleVelocity2, particleSize, particleBrightness, particleDuration, particleColor);
                engine.addHitParticle(point, particleVelocity1, particleSize, particleBrightness, particleDuration, particleColor);
                engine.addHitParticle(point, particleVelocity2, particleSize, particleBrightness, particleDuration, particleColor);					
                engine.spawnExplosion(point, particleVelocity1, particleColor, explosionSize, explosionDuration);
                engine.spawnExplosion(point, particleVelocity2, particleColor, explosionSize2, explosionDuration2);					
                Global.getSoundPlayer().playSound(soundName, pitch, volume, point, projectile.getVelocity());
            }
	}
}