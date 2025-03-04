package org.turtle.minecraft_service.repository.secondary;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.turtle.minecraft_service.domain.secondary.user.MinecraftUser;

import java.util.List;


public interface MinecraftRepository extends JpaRepository<MinecraftUser, Long> {

   MinecraftUser findByPlayerName(String nickname);

   @Query("SELECT COUNT(u) FROM MinecraftUser u WHERE u.playerName NOT IN (:gmPlayerNames)")
   int findAllUsersWithoutGM(@Param("gmPlayerNames") List<String> gmPlayerNames);

   @Query(value =
           "WITH ranked_users AS (" +
                   "    SELECT *, " +
                   "    RANK() OVER (ORDER BY progress DESC) as rank_num " +
                   "    FROM user_detailed_info " +
                   "    WHERE player_name NOT IN ('_appli_', 'Koo_pa_', '_YYH_', 'KSH_1348', 'kkOma_fan', 'uiu3111')" +
                   ") " +
                   "SELECT * " +
                   "FROM ranked_users " +
                   "WHERE rank_num <= 5 " +
                   "ORDER BY progress DESC",
           nativeQuery = true)
   List<MinecraftUser> findTop5UserWithCollection();

   @Query(value =
           "WITH ranked_users AS (" +
                   "    SELECT *, " +
                   "    RANK() OVER (ORDER BY money DESC) as rank_num " +
                   "    FROM user_detailed_info" +
                   "    WHERE player_name NOT IN ('_appli_', 'Koo_pa_', '_YYH_', 'KSH_1348', 'kkOma_fan', 'uiu3111')" +
                   ") " +
                   "SELECT * " +
                   "FROM ranked_users " +
                   "WHERE rank_num <= 5 " +
                   "ORDER BY money DESC",
           nativeQuery = true)
   List<MinecraftUser> findTop5UserWithMoney();

   @Query(value =
           "WITH ranked_users AS (" +
                   "    SELECT *, " +
                   "    RANK() OVER (ORDER BY character_level DESC) as rank_num " +
                   "    FROM user_detailed_info" +
                   "    WHERE character_class = :jobName" +
                   "    AND player_name NOT IN ('_appli_', 'Koo_pa_', '_YYH_', 'KSH_1348', 'kkOma_fan', 'uiu3111')" +
                   ") " +
                   "SELECT * " +
                   "FROM ranked_users " +
                   "WHERE rank_num <= 5 " +
                   "ORDER BY character_level DESC",
           nativeQuery = true)
   List<MinecraftUser> findTop5UserWithCombatJobLevel(@Param("jobName") String jobName);

   @Query(value =
           "WITH ranked_users AS (" +
                   "    SELECT *, " +
                   "    RANK() OVER (ORDER BY fisher_level DESC) as rank_num " +
                   "    FROM user_detailed_info" +
                   "    WHERE player_name NOT IN ('_appli_', 'Koo_pa_', '_YYH_', 'KSH_1348', 'kkOma_fan', 'uiu3111')" +
                   ") " +
                   "SELECT * " +
                   "FROM ranked_users " +
                   "WHERE rank_num <= 5 " +
                   "ORDER BY fisher_level DESC",
           nativeQuery = true
   )
   List<MinecraftUser> findTop5UserWithFishingLevel();

   @Query(value =
           "WITH ranked_users AS (" +
                   "    SELECT *, " +
                   "    RANK() OVER (ORDER BY farming_level DESC) as rank_num " +
                   "    FROM user_detailed_info" +
                   "    WHERE player_name NOT IN ('_appli_', 'Koo_pa_', '_YYH_', 'KSH_1348', 'kkOma_fan', 'uiu3111')" +
                   ") " +
                   "SELECT * " +
                   "FROM ranked_users " +
                   "WHERE rank_num <= 5 " +
                   "ORDER BY farming_level DESC",
           nativeQuery = true
   )
   List<MinecraftUser> findTop5UserWithFarmingLevel();

   @Query(value =
           "WITH ranked_users AS (" +
                   "    SELECT *, " +
                   "    RANK() OVER (ORDER BY mining_level DESC) as rank_num " +
                   "    FROM user_detailed_info" +
                   "    WHERE player_name NOT IN ('_appli_', 'Koo_pa_', '_YYH_', 'KSH_1348', 'kkOma_fan', 'uiu3111')" +
                   ") " +
                   "SELECT * " +
                   "FROM ranked_users " +
                   "WHERE rank_num <= 5 " +
                   "ORDER BY mining_level DESC",
           nativeQuery = true
   )
   List<MinecraftUser> findTop5UserWithMiningLevel();

   @Query(value =
           "WITH ranked_users AS (" +
                   "    SELECT *, " +
                   "    RANK() OVER (ORDER BY smithing_level DESC) as rank_num " +
                   "    FROM user_detailed_info" +
                   "    WHERE player_name NOT IN ('_appli_', 'Koo_pa_', '_YYH_', 'KSH_1348', 'kkOma_fan', 'uiu3111')" +
                   ") " +
                   "SELECT * " +
                   "FROM ranked_users " +
                   "WHERE rank_num <= 5 " +
                   "ORDER BY smithing_level DESC",
           nativeQuery = true
   )
   List<MinecraftUser> findTop5UserWithSmithingLevel();

   @Query(value =
           "WITH ranked_users AS (" +
                   "    SELECT *, " +
                   "    RANK() OVER (ORDER BY cooking_level DESC) as rank_num " +
                   "    FROM user_detailed_info" +
                   "    WHERE player_name NOT IN ('_appli_', 'Koo_pa_', '_YYH_', 'KSH_1348', 'kkOma_fan', 'uiu3111')" +
                   ") " +
                   "SELECT * " +
                   "FROM ranked_users " +
                   "WHERE rank_num <= 5 " +
                   "ORDER BY cooking_level DESC",
           nativeQuery = true
   )
   List<MinecraftUser> findTop5UserWithCookingLevel();

}
